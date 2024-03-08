package org.zerock.chatterup;

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

    }
}
