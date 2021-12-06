package com.marinaguasp.practicaso;

public class Race {

    private int raceMeters;
    private int minMetersRowed;
    private int maxMetersRowed;
    private Canoe winner;

    public Race(int raceMeters, int minMetersRowed, int maxMetersRowed) {
        this.raceMeters = raceMeters;
        this.minMetersRowed = minMetersRowed;
        this.maxMetersRowed = maxMetersRowed;
    }

    public int getRaceMeters() {
        return raceMeters;
    }

    public int getMinMetersRowed() {
        return minMetersRowed;
    }

    public int getMaxMetersRowed() {
        return maxMetersRowed;
    }

    public Canoe getWinner() {
        return winner;
    }

    /**
     * The first canoe that arrives at the finish line is the winner one.
     * @param canoe winner.
     */
    public synchronized void setCanoeFinished(Canoe canoe) {
        if (winner == null) {
            winner = canoe;
        }
    }

}
