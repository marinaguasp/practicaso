package com.marinaguasp.practicaso;

import android.util.Log;

public class Judge extends Thread {

    private final Race race;

    public Judge(String threadName, Race race) {
        this.race = race;
        setName(threadName);
    }

    @Override
    public void run() {
        while (!race.playersReady()) {
            // the judge is waiting for the players to be ready.
        }
        synchronized (race) {
            race.notifyAll(); //the judge orders the race to start.
        }

        while (race.allCanoesHaveNotFinished()) {
            // the judge is waiting for all the canoes to cross the finish line.
        }
        // end of the race.
        showWinner();
    }

    private void showWinner() {
        Log.d("MyTag_Judge",
                String.format("I am Judge %s. End of the race. The winner is Canoe: %s",
                        getName(), race.getWinner().getName()));
    }
}
