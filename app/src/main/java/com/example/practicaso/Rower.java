package com.example.practicaso;

import android.util.Log;

import java.util.Random;

public class Rower extends Thread {
    private final Race race;
    private final Canoe canoe;

    public Rower(String threadName, Race race, Canoe canoe) {
        this.race = race;
        this.canoe = canoe;
        setName(threadName);
    }


    @Override
    public void run() {
        while (!canoe.isFinal()) { // the race has not finished
            canoe.addReadyRowerAndWaitHelmsman(); // each rower notifies that is ready and waits
            synchronized (canoe) {
                if (!canoe.isFinal()) {
                    row(); // the rower is rowing
                }
            }
        }
    }

    private int getMetersToAdd() { // get the number of meters to row randomly
        Random random = new Random();
        return random.nextInt(race.getMaxMetersRowed() - race.getMinMetersRowed() + 1) +
                race.getMinMetersRowed();
    }

    private void row(){
            int metersToAdd = getMetersToAdd();
            canoe.addMetersRowed(metersToAdd);
            Log.d(String.format("MyTag_%s", canoe.getName()),
                    String.format("I am %s rowing %s meters.", getName(), metersToAdd));
    }
}
