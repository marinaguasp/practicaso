package com.marinaguasp.practicaso;

public class Canoe {

    private static final int TOTAL_ROWERS = 3;

    private String name;
    private int metersAdvanced;
    private boolean hasCrossedFinishLine;
    private int numberOfReadyRowers;
    private int metersRowed;

    public Canoe(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getMetersAdvanced() {
        return metersAdvanced;
    }

    public void setHasCrossedFinishLine(boolean hasCrossedFinishLine) {
        this.hasCrossedFinishLine = hasCrossedFinishLine;
    }

    public boolean hasNotCrossedFinishLine() {
        return !hasCrossedFinishLine;
    }

    public boolean rowersHaveRowed() {
        return metersRowed > 0;
    }

    public void addMetersRowed(int meters) {
        metersRowed += meters;
    }

    public void updateMetersAdvanced() {
        metersAdvanced += metersRowed;
        metersRowed = 0;
    }

    public void resetNumberOfReadyRowers() {
        numberOfReadyRowers = 0;
    }

    public synchronized boolean areRowersReady() {
        return numberOfReadyRowers == TOTAL_ROWERS;
    }

    public synchronized void addReadyRowerAndWaitHelmsman() {
        numberOfReadyRowers++;
        try {
            this.wait();
        } catch (Exception ignored) {

        }
    }
}
