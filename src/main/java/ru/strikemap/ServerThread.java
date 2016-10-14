package ru.strikemap;

import java.util.ArrayList;

/**
 * Created by maxim on 14.10.2016.
 */
public class ServerThread extends Thread {
    public final ArrayList<Player> players = new ArrayList<>();

    @Override
    public void run() {
        while (!isInterrupted()) {
            synchronized (players) {
                players.forEach(Player::update);
            }
        }
    }
}
