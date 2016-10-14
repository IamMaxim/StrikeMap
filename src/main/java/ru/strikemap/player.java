package ru.strikemap;

/**
 * Created by glebnazemnov on 14.10.16.
 */

//Каркас класса для игрока

public class player {
    private String namePlayer;
    private String team;
    private int xcoord, ycoord;
    public static boolean isAlive(){
        return false;
    }
    public static boolean isHaveAmmo(){
        return false;
    }
    public void setName(String name){
        this.namePlayer = name;
    }
    public void setTeam(String teamname){
        this.team = teamname;
    }

}
