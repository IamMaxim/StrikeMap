package ru.strikemap;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by maxim on 15.10.2016.
 */
public class Client {
    public Socket socket;
    DataOutputStream dos;
    DataInputStream dis;

    public Client(String ip, int port) throws IOException {
        socket = new Socket(ip, port);
        dos = new DataOutputStream(socket.getOutputStream());
        dis = new DataInputStream(socket.getInputStream());

        System.out.println("Client initialized");

        //Debug functions
        Net.setTeam(dos, 1);
        Net.setPos(dos, 2, 3);
        Net.setState(dos, Player.State.DEAD);

        System.out.println("Closing socket");
        socket.close();
        System.out.println("Socket closed");
    }
}
