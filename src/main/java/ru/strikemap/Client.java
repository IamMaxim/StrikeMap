package ru.strikemap;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;
import java.util.HashMap;

/**
 * Created by maxim on 15.10.2016.
 */
public class Client implements Serializable {
    public Socket socket;
    public DataOutputStream dos;
    public DataInputStream dis;
    public ClientThread clientThread;
    public Player player;

    public volatile HashMap<Integer, Player> players = new HashMap<>();

    public Client(String ip, int port) throws IOException {
        socket = new Socket(ip, port);
        dos = new DataOutputStream(socket.getOutputStream());
        dis = new DataInputStream(socket.getInputStream());
        clientThread = new ClientThread(this);
        clientThread.start();
        System.out.println("Client initialized");
    }
}
