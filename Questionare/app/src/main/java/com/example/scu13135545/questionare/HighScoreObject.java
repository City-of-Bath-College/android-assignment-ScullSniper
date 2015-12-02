package com.example.scu13135545.questionare;

/**
 * Created by scu13135545 on 04/11/2015.
 */
public class HighScoreObject {
    public int score;
    public String name;
    public long timestamp;

    public HighScoreObject( int score,String name, long timestamp){

        this.score = score;

    }

    public HighScoreObject() {
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public long getTimestamp() {
        return timestamp;
    }

}
