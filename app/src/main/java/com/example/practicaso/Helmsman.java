package com.example.practicaso;

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
        while (!canoe.isFinal()) {
            while (!canoe.areRowersReady()) {
            }
            // rowers ready
            synchronized (canoe) {
                canoe.resetNumberOfReadyRowers();
                updateCanoeState();
                canoe.notifyAll(); // notify rowers
            }
        }
        // canoe finished
        race.setCanoeFinished(canoe);
    }

    public void updateCanoeState(){
        if (canoe.hasStarted()) {
            canoe.updateMetersAdvanced();
            showMetersRowed();
            canoe.setFinal(canoe.getMetersAdvanced() >= race.getRaceMeters());
        }
    }

    public void showMetersRowed(){
        Log.d(String.format("MyTag_%s", canoe.getName()),
                String.format("I am %s and we have already rowed %s meters.",
                        getName(), canoe.getMetersAdvanced()));
    }
}
