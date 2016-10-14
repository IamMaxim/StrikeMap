package ru.strikemap;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by maxim on 14.10.2016.
 */
public class ServerThread extends Thread {
    private final HashMap<Integer, Player> players = new HashMap<>();

    public void add(Player player) {
        synchronized (players) {
            players.put(player.id, player);
        }
    }

    public void remove(int id) {
        synchronized (players) {
            players.remove(id);
        }
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            synchronized (players) {
                players.values().forEach(Player::update);
            }
        }
    }
}
