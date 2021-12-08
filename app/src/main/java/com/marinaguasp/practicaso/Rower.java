package com.marinaguasp.practicaso;

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
        race.addReadyPlayerAndWaitTheJudge();

        while (canoe.hasNotCrossedFinishLine()) {
            canoe.addReadyRowerAndWaitHelmsman(); // each rower notifies that is ready and waits
            synchronized (canoe) {
                if (canoe.hasNotCrossedFinishLine()) {
                    row(); // the rower is rowing
                }
            }
        }
    }

    private void row() {
        int metersToRow = getMetersToRow();
        canoe.addMetersRowed(metersToRow);
        Log.d(String.format("MyTag_%s", canoe.getName()),
                String.format("I am %s rowing %s meters.", getName(), metersToRow));
        try {
            Thread.sleep(metersToRow);  // simulate rowing effort.
        } catch (InterruptedException ignored) {
        }
    }

    /**
     * Get the meters to row randomly.
     *
     * @return randomly meters to row.
     */
    private int getMetersToRow() {
        Random random = new Random();
        return random.nextInt(race.getMaxMetersRowed() - race.getMinMetersRowed() + 1) +
                race.getMinMetersRowed();
    }
}
