package ru.strikemap;

/**
 * Created by maxim on 14.10.2016.
 */
public class Player {
    private static int ID_TO_ADD = 0;

    public int id;
    public float x, y;
    public State state;
    public int team;

    public Player(int team, float x, float y) {
        id = ID_TO_ADD++;

//avoid int overflow
        if (ID_TO_ADD == Integer.MAX_VALUE) ID_TO_ADD = 0;
        this.team = team;
        this.x = x;
        this.y = y;
        state = State.NORMAL;
    }

    public void update() {

    }

    enum State {
        NORMAL,
        DEAD,
        INACTIVE
    }
}