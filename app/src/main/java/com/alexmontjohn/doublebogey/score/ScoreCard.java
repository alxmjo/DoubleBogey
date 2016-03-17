package com.alexmontjohn.doublebogey.score;

import java.util.ArrayList;

public class ScoreCard {

    public ArrayList<String> holes;
    public ArrayList<Integer> scores;

    public ScoreCard(int h) {
        holes = new ArrayList<String>();
        for (int i = 1; i <= h; i++) {
            holes.add("Hole " + i);
        }

        scores = new ArrayList<Integer>();
        for (int i = 1; i <= h; i++) {
            scores.add(0);
        }
    }

}
