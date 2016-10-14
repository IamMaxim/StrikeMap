package ru.strikemap;

/**
 * Created by maxim on 14.10.2016.
 */
public class Player {
    public float x, y;
    public State state;
    public int team;

    public Player(int team, float x, float y) {
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
