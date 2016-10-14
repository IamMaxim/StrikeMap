package ru.strikemap;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by maxim on 14.10.2016.
 */
public class Net {
    private static int ACTION_TO_ADD = 0;

    public static final int ACTION_SET_STATE = ACTION_TO_ADD++,
            ACTION_SET_POS = ACTION_TO_ADD++,
            ACTION_SET_TEAM = ACTION_TO_ADD++;

    public void setState(DataOutputStream dos, Player.State state) throws IOException {
        dos.writeByte(state.ordinal());
    }

    public void setPos(DataOutputStream dos, float x, float y) throws IOException {
        dos.writeFloat(x);
        dos.writeFloat(y);
    }
}
