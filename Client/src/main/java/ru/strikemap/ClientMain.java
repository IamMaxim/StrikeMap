package ru.strikemap;

import java.io.IOException;

/**
 * Created by maxim on 15.10.2016.
 */
public class ClientMain {
    public static void main(String[] args) {
        try {
            new Client("localhost", 52464);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
