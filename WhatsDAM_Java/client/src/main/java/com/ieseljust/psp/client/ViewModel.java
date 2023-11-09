package com.ieseljust.psp.client;

import java.util.ArrayList;
import org.json.JSONObject;

public class ViewModel {
    static ViewModel INSTANCE = new ViewModel();
    public static ArrayList<Message> llistaMissatges = new ArrayList<Message>();
    public static ArrayList<String> llistaUsuaris = new ArrayList<String>();

    public static ViewModel getInstance() {
        return INSTANCE;
    }

    public ArrayList<String> getLlistaUsuaris() {
        try {
            return llistaUsuaris;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public ArrayList<Message> getPendingMessages() {
        try {
            ArrayList<Message> ret = new ArrayList<>(llistaMissatges);
            llistaMissatges.clear();
            return ret;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public void updateUserList(ArrayList<String> users) {
        llistaUsuaris = users;
    }

    public void addMessage(Message msg) {
        llistaMissatges.add(msg);
    }
}
