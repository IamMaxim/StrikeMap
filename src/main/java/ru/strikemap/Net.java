package ru.strikemap;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by maxim on 14.10.2016.
 */
public class Net {
    private static int ACTION_TO_ADD = 0;

    //codes for actions
    public static final int
            ACTION_SET_STATE = ACTION_TO_ADD++,
            ACTION_SET_POS = ACTION_TO_ADD++,
            ACTION_SET_TEAM = ACTION_TO_ADD++;

    public static void setState(DataOutputStream dos, Player.State state) throws IOException {
        dos.writeByte(ACTION_SET_STATE);
        dos.writeByte(state.ordinal());
    }

    public static void setPos(DataOutputStream dos, float x, float y) throws IOException {
        dos.writeByte(ACTION_SET_POS);
        dos.writeFloat(x);
        dos.writeFloat(y);
    }

    public static void setTeam(DataOutputStream dos, int team) throws IOException {
        dos.writeByte(ACTION_SET_TEAM);
        dos.writeInt(team);
    }
}
