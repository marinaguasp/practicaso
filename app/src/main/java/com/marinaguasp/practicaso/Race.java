package com.marinaguasp.practicaso;

public class Race {

    private static final int TOTAL_CANOES = 2;
    private static final int TOTAL_PLAYERS = 8;

    private int raceMeters;
    private int minMetersRowed;
    private int maxMetersRowed;
    private Canoe winner;
    private int canoesFinished;
    private int playersReady;

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
     *
     * @param canoe winner.
     */
    public synchronized void setCanoeFinished(Canoe canoe) {
        canoesFinished++;
        if (winner == null) {
            winner = canoe;
        }
    }

    public synchronized boolean allCanoesHaveNotFinished() {
        return canoesFinished < TOTAL_CANOES;
    }

    public synchronized boolean playersReady() {
        return playersReady == TOTAL_PLAYERS;
    }

    public synchronized void addReadyPlayerAndWaitTheJudge() {
        playersReady++;
        try {
            wait();
        } catch (InterruptedException ignored) {
        }
    }

}
