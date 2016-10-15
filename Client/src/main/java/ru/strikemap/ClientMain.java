package ru.strikemap;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by maxim on 15.10.2016.
 */
public class ClientMain {
    public static void main(String[] args) {
        try {
            Client client = new Client("localhost", 52464);

            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()) {
                String s = scanner.nextLine();
                String[] tokens = s.split(" ");
                String command = tokens[0];
                System.out.println("passed command: " + command);
                if (command.equals("setState")) {
                    System.out.println("state: " + tokens[1]);
                    Net.sendStateToServer(client.dos, Player.State.values()[Integer.parseInt(tokens[1])]);
                } else if (command.equals("setTeam")) {
                    Net.sendTeamToServer(client.dos, Integer.parseInt(tokens[1]));
                } else if (command.equals("setPos")) {
                    Net.sendCoordToServer(client.dos, Float.parseFloat(tokens[1]), Float.parseFloat(tokens[2]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
