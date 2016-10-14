package ru.strikemap;

import java.io.IOException;

/**
 * Created by maxim on 14.10.2016.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        Server server = new Server(52464);
        Client client = new Client("localhost", 52464);
    }
}