package org.zerock.client;

import java.net.Socket;

import javafx.application.Application;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.application.Platform;

public class Main extends Application {
    Socket socket;
    TextArea textArea;

    // Method to start client.
    public void startClient(String IP, int port) {
        Thread thread = new Thread() {
            public void run() {
                try {
                    socket = new Socket(IP, port);
                    receive();
                } catch (Exception e) {
                    if(!socket.isClosed()) {
                        stopClient();
                        System.out.println("[Cannot connect to the server]");
                        Platform.exit();
                    }
                }
            }
        };
        thread.start();
    }
    // Method to stop client.
    public void stopClient() {

    }
    // Method to receive messages from the server.
    public void receive() {

    }
    // Method to send messages to the server.
    public void send(String message) {

    }
    // Method to start the application.
    @Override
    public void start(Stage primaryMessage) {

    }
    // Main method.
    public static void main(String[] args) {
        launch(args);
    }
}