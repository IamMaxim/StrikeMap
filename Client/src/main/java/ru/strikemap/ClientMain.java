package ru.strikemap;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by maxim on 15.10.2016.
 */
public class ClientMain {
    public static void main(String[] args) {
        try {
            String ip = "localhost";
            int port = 52464;
            if (args.length > 0) ip = args[0];
            if (args.length > 1) port = Integer.parseInt(args[1]);
            Client client = new Client(ip, port);

            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()) {
                String s = scanner.nextLine();
                String[] tokens = s.split(" ");
                String command = tokens[0];
                if (command.equals("setState")) {
                    Net.sendStateToServer(client.dos, Player.State.values()[Integer.parseInt(tokens[1])]);
                } else if (command.equals("setTeam")) {
                    Net.sendTeamToServer(client.dos, Integer.parseInt(tokens[1]));
                } else if (command.equals("setPos")) {
                    Net.sendCoordToServer(client.dos, Float.parseFloat(tokens[1]), Float.parseFloat(tokens[2]));
                } else if (command.equals("disconnect")) {
                    client.socket.close();
                } else if (command.equals("setName")) {
                    Net.sendNameToServer(client.dos, tokens[1]);
                } else if (command.equals("list")) {
                    client.players.forEach((id, p) -> {
                        System.out.println("id: " + p.id + " team: " + p.team + " " + p.name + " [" + p.x + ", " + p.y + "] " + p.state);
                    });
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
