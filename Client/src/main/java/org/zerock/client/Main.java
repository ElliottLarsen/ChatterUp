package org.zerock.client;

import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // Method to receive messages from the server.
    public void receive() {
        while (true) {
            try {
                InputStream in = socket.getInputStream();
                byte[] buffer = new byte[512];
                int length = in.read(buffer);
                if (length == -1) {
                    throw new IOException();
                }
                String message = new String(buffer, 0, length, "UTF-8");
                Platform.runLater(() -> {
                    textArea.appendText(message);
                });
            } catch (Exception e) {
                stopClient();
                break;
            }
        }
    }
    // Method to send messages to the server.
    public void send(String message) {
        Thread thread = new Thread() {
            public void run() {
                try {
                    OutputStream out = socket.getOutputStream();
                    byte[] buffer = message.getBytes("UTF-8");
                    out.write(buffer);
                    out.flush();
                } catch (Exception e) {
                    stopClient();
                }
            }
        };
        thread.start();
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