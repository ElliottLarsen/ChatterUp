package org.zerock.chatterup;


import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.application.Application;
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