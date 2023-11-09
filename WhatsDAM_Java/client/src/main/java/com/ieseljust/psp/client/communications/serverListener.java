package com.ieseljust.psp.client.communications;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import com.ieseljust.psp.client.CurrentConfig;
import com.ieseljust.psp.client.Message;
import com.ieseljust.psp.client.ViewModel;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;

import org.json.JSONObject;
import org.json.JSONArray;

public class serverListener implements Runnable {

    ViewModel vm;

    public serverListener(ViewModel vm) {
        this.vm = vm;
    }

    int listenerPort = CurrentConfig.getListenPort();

    @Override
    public void run() {
        // 1. Crear un socket de tipo servidor que escuche por el puerto de recepción de mensajes
        ServerSocket listener = null;
        try {
            // Creamos el socket en un puerto determinado por el sistema
            // y lo guardamos en listenPort.
            listener = new ServerSocket(0);
            CurrentConfig.setListenPort(listener.getLocalPort());
        } catch (IOException e) {
            System.out.println("El puerto " + listenerPort + " está ocupado o es inaccesible.");
            return;
        }

        // TO-DO
        // 2. Iniciamos un bucle infinito a la espera de recibir conexiones
        // Cuando llegue una conexión, la procesaremos de manera adecuada
        // Las peticiones que podemos recibir serán de tipo:

        // {"type": "userlist", "content": [Lista de usuarios]}, con un JSONArray con la lista de usuarios.
        // {"type": "message", "user":"usuario", "content": "Contenido del mensaje" }

        // Es interesante implementar un método aparte para procesar estas líneas
        // pero no es necesario crear un hilo adicional para atender cada mensaje, ya que
        // no somos un servidor como tal, y lo que estamos haciendo aquí es mantener un
        // canal de recepción solo con el servidor.

        while (true) {
            try {
                Socket socket = listener.accept();
                processRequest(socket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void processRequest(Socket socket) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String request = reader.readLine();

            JSONObject jsonRequest = new JSONObject(request);

            String type = jsonRequest.getString("type");

            if ("userlist".equals(type)) {
                JSONArray userList = jsonRequest.getJSONArray("content");
                // Procesa la lista de usuarios (userList) como desees
            } else if ("message".equals(type)) {
                String user = jsonRequest.getString("user");
                String content = jsonRequest.getString("content");
                // Procesa el mensaje recibido (user y content) como desees
            }

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
