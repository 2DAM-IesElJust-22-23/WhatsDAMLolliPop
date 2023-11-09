package com.ieseljust.psp.client;

public class CurrentConfig {
    private static String _username = "";
    private static String _server = "127.0.0.1";
    private static int _port = 9999;
    private static int _listenPort = 0;

    public static void init(String server, String username) {
        _username = username;
        _server = server;
    }

    public static String getServerAddress() {
        return _server;
    }

    public static String getUsername() {
        return _username;
    }

    public static int getServerPort() {
        return _port;
    }

    public static Boolean connectionReady() {
        return _listenPort != 0;
    }

    public static int getListenPort() {
        return _listenPort;
    }

    public static void setListenPort(int port) {
        System.out.println("Setting Listen Port to " + port);
        _listenPort = port;
    }
}
