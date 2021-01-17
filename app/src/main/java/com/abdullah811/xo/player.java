package com.abdullah811.xo;

import java.security.PrivateKey;

public class player {

    private String choose;
    private String name;
    private int Score;
    public player(String choose,String name,int Score) {
        this.choose = choose;
        this.name = name;
        this.Score = Score;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }

    public String getChoose() {
        return choose;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setChoose(String choose) {
        this.choose = choose;
    }

}
