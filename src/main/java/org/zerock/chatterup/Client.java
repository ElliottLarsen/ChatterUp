package org.zerock.chatterup;

import java.io.OutputStream;
import java.net.Socket;

public class Client {
    Socket socket;

    public Client(Socket socket) {
        this.socket = socket;
        receive();
    }
    // Method to receive messages from a client.
    public void receive() {

    }

    // Method to send messages to a client.
    public void send(String message) {
        Runnable thread = new Runnable() {
            @Override
            public void run() {
                try {
                    OutputStream out = socket.getOutputStream();
                    byte[] buffer = message.getBytes("UTF-8");
                    out.write(buffer);
                    out.flush();
                } catch (Exception e) {
                    try {
                        System.out.println("[Message send error] "
                                + socket.getRemoteSocketAddress() + ": "
                                + Thread.currentThread().getName());
                        Main.clients.remove(Client.this);
                        socket.close();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
        };
        Main.threadPool.submit(thread);
    }
}
