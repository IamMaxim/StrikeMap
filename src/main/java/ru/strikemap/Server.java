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
    private ServerThread serverThread;

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        serverThread = new ServerThread();

        while (!serverSocket.isClosed()) {
            try {
                Socket s = serverSocket.accept();
                Client client;
                clients.add(client = new Client(s));
                serverThread.add(new Player(0, 0, 0));
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
                        byte action = is.readByte();
                        if (action == Net.ACTION_SET_STATE) {
                            player.state = Player.State.values()[is.readInt()];
                        } else if (action == Net.ACTION_SET_POS) {
                            player.x = is.readFloat();
                            player.y = is.readFloat();
                        } else if (action == Net.ACTION_SET_TEAM) {
                            player.team = is.readInt();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
