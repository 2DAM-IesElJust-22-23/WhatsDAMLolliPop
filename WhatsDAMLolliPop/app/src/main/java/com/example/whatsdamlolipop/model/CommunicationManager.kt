package com.example.whatsdamlolipop.model

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.ServerSocket
import java.net.Socket

object CommunicationManager {

    private const val PORT = 9999
    private var server: String? = null
    private var listenPort: Int? = null

    suspend fun enviarServidor(msg: String): JSONObject {
        return withContext(Dispatchers.IO) {
            val socket = Socket(server, PORT)
            val writer = OutputStreamWriter(socket.getOutputStream())
            val reader = BufferedReader(InputStreamReader(socket.getInputStream()))

            try {
                // Enviar mensaje al servidor
                writer.write(msg)
                writer.flush()

                // Leer la respuesta del servidor
                val response = StringBuilder()
                var line: String?
                while (reader.readLine().also { line = it } != null) {
                    response.append(line)
                }

                // Devolver la respuesta en formato JSON
                JSONObject(response.toString())
            } finally {
                // Cerrar streams y socket
                writer.close()
                reader.close()
                socket.close()
            }
        }
    }

    suspend fun prepareListener() {
        listenPort = getFreePort()
        GlobalScope.launch(Dispatchers.IO) {
            val serverSocket = ServerSocket(listenPort!!)
            while (true) {
                val clientSocket = serverSocket.accept()
                GlobalScope.launch(Dispatchers.IO) {
                    processNotification(clientSocket)
                }
            }
        }
    }

    private suspend fun processNotification(socket: Socket) {
        val reader = BufferedReader(InputStreamReader(socket.getInputStream()))
        val message = reader.readLine()

        // Procesar la notificación
        val jsonMessage = JSONObject(message)
        val messageType = jsonMessage.getString("type")

        when (messageType) {
            "message" -> {
                // Procesar el mensaje y agregarlo a la lista de mensajes
                val content = jsonMessage.getString("content")
                Missatges.add("Remitente", content)

                // Enviar respuesta al servidor
                val response = JSONObject().put("status", "ok")
                val writer = OutputStreamWriter(socket.getOutputStream())
                writer.write(response.toString())
                writer.flush()

                // Cerrar streams y socket
                writer.close()
                reader.close()
                socket.close()
            }
        }
    }

    suspend fun login(usuario: String, contrasena: String): JSONObject {
        return try {
            prepareListener()
            val registerMessage = JSONObject().apply {
                put("command", "register")
                put("user", usuario)
                put("listenPort", listenPort)
            }
            enviarServidor(registerMessage.toString())
        } catch (e: Exception) {
            JSONObject().put("status", "error")
        }
    }

    private fun getFreePort(): Int {
        return try {
            val serverSocket = ServerSocket(0)
            val port = serverSocket.localPort
            serverSocket.close()
            port
        } catch (e: Exception) {
            throw RuntimeException("Error al obtener un puerto disponible", e)
        }
    }
}