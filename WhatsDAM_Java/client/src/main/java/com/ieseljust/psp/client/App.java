package com.ieseljust.psp.client;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.Parent;
import javafx.stage.Stage;

import com.ieseljust.psp.client.communications.*;

public class App extends Application {

    public void init() {
        // Init inicializa las variables de la aplicación.
        // En principio, aquí no es necesario hacer nada.
    }

    @Override
    public void start(Stage stage) {
        try {
            // Características generales de la aplicación

            // Archivo de vista
            String fxml = "mainLayout.fxml";

            // Cargar la vista
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(fxml));
            Scene scene = new Scene(root);

            // Establecer propiedades para la ventana (stage)
            stage.setTitle("WhatsDAM");
            stage.setMaxHeight(600);
            stage.setMaxWidth(800);
            stage.setResizable(true);

            // También podrías configurar el ícono de la aplicación aquí
            // o agregar estilos personalizados.

            var appIcon = new Image("icon.png");
            stage.getIcons().add(appIcon);

            // Estilos personalizados

            // Preparar la aplicación para que se cierre al cerrar la ventana
            stage.setOnCloseRequest(e -> {
                Platform.exit();
                System.exit(0);
            });

            // Establecer la escena (vista) y mostrar la ventana (stage)
            stage.setScene(scene);
            stage.show();

            try {
                // Lanzar un hilo dentro de la interfaz gráfica
                // para actualizar periódicamente la lista de usuarios

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Runnable updater = new Runnable() {
                            @Override
                            public void run() {
                                // Actualizar periódicamente la lista de usuarios.
                                // Recupérala del modelo y pásala a la interfaz de usuario.
                                mainLayoutController.updateUsuaris(ViewModel.getInstance().getLlistaUsuaris());
                                mainLayoutController.updateMessages(ViewModel.getInstance().getPendingMessages());
                            }
                        };

                        // Esto hace que se ejecute periódicamente.
                        // Platform.runLater permite lanzar un hilo
                        // dentro de la interfaz.
                        while (true) {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException ex) {
                            }

                            // La actualización de la interfaz de usuario se ejecuta en el hilo de la aplicación
                            Platform.runLater(updater);
                        }
                    }
                });
                // No permitir que el hilo evite que se cierre la JVM
                thread.setDaemon(true);
                thread.start();

            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CurrentConfig.init(args[0], args[1]);

        // El ViewModel será un objeto compartido
        ViewModel vm = new ViewModel();

        // Lanzar el hilo para escuchar las emisiones del servidor
        serverListener sl = new serverListener(vm);
        new Thread(sl).start();

        try {
            // CommunicationManager gestiona las conexiones al servidor,
            // envía mensajes al servidor y gestiona las respuestas.
            // Debemos esperar a que el puerto se establezca correctamente.
            while (!CurrentConfig.connectionReady()) {
                System.exit(Integer.parseInt("Configuración no está lista"));
                Thread.sleep(100);
            }
            System.out.println("Configuración lista");
            communicationManager.connect();

            // Iniciar el ciclo de vida de la aplicación gráfica
            launch(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
