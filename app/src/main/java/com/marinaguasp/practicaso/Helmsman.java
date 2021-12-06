package com.marinaguasp.practicaso;

import android.util.Log;

public class Helmsman extends Thread {

    private final Race race;
    private final Canoe canoe;

    public Helmsman(String threadName, Race race, Canoe canoe) {
        this.race = race;
        this.canoe = canoe;
        setName(threadName);
    }

    @Override
    public void run() {
        race.addReadyPlayerAndWaitTheJudge();

        while (canoe.hasNotCrossedFinishLine()) {
            while (!canoe.areRowersReady()) {
                // helmsman is waiting for the rowers to be ready
            }
            //update the canoe state and notify the rowers.
            synchronized (canoe) {
                canoe.resetNumberOfReadyRowers();
                updateCanoeState();
                canoe.notifyAll(); // notify rowers
            }
        }
        // canoe finished
        race.setCanoeFinished(canoe);
    }

    /**
     * If rowers have rowed, the helmsman has to update total meters advanced and show the advance
     * in the debug console.
     * Finally, the helmsman has to update whether the canoe has crossed the end line.
     */
    private void updateCanoeState() {
        if (canoe.rowersHaveRowed()) {
            canoe.updateMetersAdvanced();
            showMetersRowed();
            canoe.setHasCrossedFinishLine(canoe.getMetersAdvanced() >= race.getRaceMeters());
        }
    }

    private void showMetersRowed() {
        Log.d(String.format("MyTag_%s", canoe.getName()),
                String.format("I am %s and we have already rowed %s meters.",
                        getName(), canoe.getMetersAdvanced()));
    }
}
