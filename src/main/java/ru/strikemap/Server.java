package ru.strikemap;

import java.io.*;
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
        public DataInputStream is;
        public DataOutputStream os;
        public Socket socket;

        public Client(Socket socket) throws IOException {
            this.socket = socket;
            is = new DataInputStream(socket.getInputStream());
            os = new DataOutputStream(socket.getOutputStream());
        }

        private class ConnectionUpdateThread extends Thread {
            private Player player;

            ConnectionUpdateThread(Player player) {
                this.player = player;
            }

            @Override
            public void run() {
                while (!isInterrupted()) {
                    try {
                        int action = is.read();
                        if (action == Net.ACTION_SET_STATE) {
                            player.x = is.readFloat();
                            player.y = is.readFloat();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
