package org.zerock.chatterup;


import java.net.ServerSocket;
import java.util.Vector;
import java.util.concurrent.ExecutorService;

import javafx.application.Application;
import javafx.stage.Stage;
public class Main extends Application {
    public static ExecutorService threadPool;
    public static Vector<Client> clients = new Vector<Client>();
    ServerSocket serverSocket;

    // Method to start the server and wait for client connection.
    public void startServer(String IP, int port) {

    }

    // Method to stop server.
    public void stopServer() {

    }

    // Method to create UI and starts the application.
    @Override
    public void start(Stage primaryStage) {

    }

    // Main method,
    public static void main(String[] args) {
        launch(args);
    }
}