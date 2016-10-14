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
    private Thread connectionAcceptor;

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        serverThread = new ServerThread();
        serverThread.start();

        System.out.println("Server initialized");

        connectionAcceptor = new Thread(() -> {
            while (!serverSocket.isClosed()) {
                try {
                    Socket s = serverSocket.accept();
                    Client client;
                    clients.add(client = new Client(s));
                    serverThread.add(client.player);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        connectionAcceptor.start();
    }

    public class Client {
        //streams for transferring data
        public DataInputStream dis;
        public DataOutputStream dos;
        public Socket socket;
        //on-server player object
        public Player player = new Player(0, 0, 0);
        //update data in background
        ConnectionUpdateThread connectionUpdateThread;

        public Client(Socket socket) throws IOException {
            this.socket = socket;
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
            connectionUpdateThread = new ConnectionUpdateThread(this);
            connectionUpdateThread.start();
        }

        private class ConnectionUpdateThread extends Thread {
            private Client client;
            private Player player;

            ConnectionUpdateThread(Client client) {
                this.client = client;
                this.player = client.player;
            }

            @Override
            public void run() {
                while (!isInterrupted()) {
                    try {
                        //read updates from client
                        byte action = dis.readByte();
                        if (action == Net.ACTION_SET_STATE) {
                            System.out.println("Setting state");
                            player.state = Player.State.values()[dis.readInt()];
                        } else if (action == Net.ACTION_SET_POS) {
                            System.out.println("Setting position");
                            player.x = dis.readFloat();
                            player.y = dis.readFloat();
                        } else if (action == Net.ACTION_SET_TEAM) {
                            System.out.println("Setting team");
                            player.team = dis.readInt();
                        }
                    } catch (EOFException e) {
                        //EOF means socket is closed
                        System.out.println("Removing client from server");

                        //cleanup player lists
                        serverThread.remove(client.player.id);
                        clients.remove(client);
                        break;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
