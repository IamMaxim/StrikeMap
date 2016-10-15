package ru.strikemap;

import java.io.IOException;

/**
 * Created by maxim on 15.10.2016.
 */
public class ServerMain {
    public static void main(String[] args) {
        try {
            new Server(52464);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
