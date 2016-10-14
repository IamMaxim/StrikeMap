package ru.strikemap;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by maxim on 14.10.2016.
 */
public class Server {
    private ServerSocket serverSocket;
    private ArrayList<Client> clients = new ArrayList<>();

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);

        while (!serverSocket.isClosed()) {
            try {
                Socket s = serverSocket.accept();
                Client client;
                clients.add(client = new Client(s));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public class Client {
        public InputStream is;
        public OutputStream os;
        public Socket socket;

        public Client(Socket socket) throws IOException {
            this.socket = socket;
            is = socket.getInputStream();
            os = socket.getOutputStream();
        }
    }
}
