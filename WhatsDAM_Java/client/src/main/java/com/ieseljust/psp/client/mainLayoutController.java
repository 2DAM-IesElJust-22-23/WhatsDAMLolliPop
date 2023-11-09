package com.ieseljust.psp.client;

import java.util.ArrayList;

import com.ieseljust.psp.client.communications.communicationManager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;

public class mainLayoutController {
    @FXML
    private Button sendBt;
    @FXML
    private ListView<Message> messageList;
    @FXML
    private ListView<String> userList;
    @FXML
    private TextArea message;

    public static ObservableList<Message> llistaMissatges = FXCollections.observableArrayList();
    public static ObservableList<String> llistaUsuaris = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        messageList.setCellFactory(new itemViewFactory());
        messageList.setItems(llistaMissatges);
        userList.setItems(llistaUsuaris);

        sendBt.setOnMouseClicked((event) -> enviarMisstge());

        message.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER)
                enviarMisstge();
        });
    }

    public static void updateUsuaris(ArrayList<String> llista) {
        try {
            llistaUsuaris.clear();
            for (String item : llista)
                llistaUsuaris.add(item);
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
        }
    }

    public static void updateMessages(ArrayList<Message> llista) {
        try {
            for (Message item : llista)
                llistaMissatges.add(item);
        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
        }
    }

    private void enviarMisstge() {
        if (!message.getText().equals("\n")) {
            Message msg = new Message(CurrentConfig.getUsername(), message.getText());
            communicationManager.sendMessage(msg);
        }
        message.setText("");
    }
}
