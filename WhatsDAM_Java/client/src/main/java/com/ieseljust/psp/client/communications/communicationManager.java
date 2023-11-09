package com.ieseljust.psp.client.communications;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

import com.ieseljust.psp.client.CurrentConfig;
import com.ieseljust.psp.client.Message;

import org.json.JSONException;
import org.json.JSONObject;

public class communicationManager {

    public static JSONObject sendServer(String msg) {
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(CurrentConfig.getServerAddress(), CurrentConfig.getServerPort()));

            // Envia el missatge al servidor
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter out = new PrintWriter(new OutputStreamWriter(outputStream));
            out.println(msg);
            out.flush();

            // Rep la resposta del servidor
            InputStream inputStream = socket.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
            String response = in.readLine();

            // Tanca la connexió
            socket.close();

            // Parsa la resposta JSON
            return new JSONObject(response);
        } catch (Exception e) {
            e.printStackTrace();
            // Maneja errors de connexió o JSON
            return null;
        }
    }

    public static void connect() throws JSONException, communicationManagerException {
        try {
            JSONObject registerData = new JSONObject();
            registerData.put("command", "register");
            registerData.put("user", CurrentConfig.getUsername());
            registerData.put("listenPort", CurrentConfig.getListenPort());

            // Envia el missatge de registre al servidor
            sendServer(registerData.toString());
        } catch (Exception e) {
            e.printStackTrace();
            // Maneja errors de connexió o JSON
            throw new communicationManagerException("Error durant el registre");
        }
    }

    public static void sendMessage(Message m) {
        // Implementa l'enviament d'un missatge al servidor
    }

    public static class communicationManagerException extends Exception {
        public communicationManagerException(String message) {
            super(message);
        }
    }
}
