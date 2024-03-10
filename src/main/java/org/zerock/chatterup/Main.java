package org.zerock.chatterup;


import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
public class Main extends Application {
    public static ExecutorService threadPool;
    public static Vector<Client> clients = new Vector<Client>();
    ServerSocket serverSocket;

    // Method to start the server and wait for client connection.
    public void startServer(String IP, int port) {
        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(IP, port));
        } catch (Exception e) {
            e.printStackTrace();
            if (!serverSocket.isClosed()) {
                stopServer();
            }
            return;
        }

        // This thread waits for a client to connect.
        Runnable thread = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Socket socket = serverSocket.accept();
                        clients.add(new Client(socket));
                        System.out.println("[Client Connected] "
                                + socket.getRemoteSocketAddress() + ": "
                                + Thread.currentThread().getName());
                    } catch (Exception e) {
                        if (!serverSocket.isClosed()) {
                            stopServer();
                        }
                        break;
                    }
                }
            }
        };
        threadPool = Executors.newCachedThreadPool();
        threadPool.submit(thread);
    }

    // Method to stop server.
    public void stopServer() {
        try {
            // Close all sockets.
            Iterator<Client> iterator = clients.iterator();
            while (iterator.hasNext()) {
                Client client = iterator.next();
                client.socket.close();
                iterator.remove();
            }
            // Close the server socket object.
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
            // Thread pool termination.
            if (threadPool != null && !threadPool.isShutdown()) {
                threadPool.shutdown();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to create UI and starts the application.
    @Override
    public void start(Stage primaryStage) {
        // Set up for the UI.
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(5));

        TextArea textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Ariel", 15));
        root.setCenter(textArea);

        Button toggleButton = new Button("Start");
        toggleButton.setMaxWidth((Double.MAX_VALUE));
        BorderPane.setMargin(toggleButton, new Insets(1, 0, 0, 0));
        root.setBottom(toggleButton);

        String IP = "127.0.0.1";
        int port = 8005;

        // Start/Stop the server and change the button accordingly.
        toggleButton.setOnAction(event -> {
            if (toggleButton.getText().equals("Start")) {
                startServer(IP, port);
                Platform.runLater(() -> {
                    String message = String.format("[Start Server]\n", IP, port);
                    textArea.appendText(message);
                    toggleButton.setText("Stop");
                });
            } else {
                stopServer();
                Platform.runLater(() -> {
                    String message = String.format("[Stop Server]\n", IP, port);
                    textArea.appendText(message);
                    toggleButton.setText("Start");
                });
            }
        });
        Scene scene = new Scene(root, 400, 400);
        primaryStage.setTitle("[ChatterUp Server]");
        primaryStage.setOnCloseRequest(event -> stopServer());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Main method,
    public static void main(String[] args) {
        launch(args);
    }
}