# ChatterUp
ChatterUp is a chat application that allows users to connect to a server and chat with each other in real-time. It consists of two modules: Server and Client, each responsible for handling server-side and client-side functionality, respectively.

## Technologies
- Java
- JavaFX

## Key Features
- **Real-time Communication:** Users can send and receive messages in real-time.
- **Server-Client Architecture:** Utilizes a server-client architecture for handling multiple connections.
- **Thread Pool Management:** Efficiently manages concurrent tasks, such as handling multiple client connections, using a thread pool.

## Project Details
### Server Module
The Server module handles the backend functionality of the application, including:
- **Server Startup:** Starts the server and listens for incoming client connections on a specified IP address and port.
- **Client Connection Handling:** Accepts incoming client connections and manages them using a thread pool for concurrency.
- **Message Broadcasting:** Broadcasts messages received from one client to all connected clients in real-time.

### Client Module
The Client module is responsible for the frontend functionality of the application, including:
- **User Interface:** Provides a graphical user interface for users to enter their username, server IP address, and port number.
- **Connection Management:** Connects to the server and sends/receives messages in real-time.
- **Message Sending:** Allows users to send messages to the server, which are then broadcasted to all connected clients.

## GIF Walkthrough
<p>
<image src='chatterup.gif' width = 650 height = 650><br>

## Contact
Elliott Larsen
* Email elliottlrsn@gmail.com
* GitHub [@elliottlarsen](https://github.com/elliottlarsen)
* LinkedIn [@elliottlarsen](https://www.linkedin.com/in/elliottlarsen)