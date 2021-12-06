package com.example.practicaso;

public class Canoe {
    private String name;
    private int metersAdvanced;
    private boolean isFinal;
    private int numberOfReadyRowers;
    private int metersRowed;

    public Canoe(String name) {
        this.name = name;
    }

    public synchronized boolean isFinal() {
        return isFinal;
    }

    public synchronized void addReadyRowerAndWaitHelmsman() {
        numberOfReadyRowers++;
        try {
            this.wait();
        } catch (Exception ignored) {

        }
    }

    public void addMetersRowed(int meters) {
        metersRowed += meters;
    }

    public synchronized boolean areRowersReady() {
        return numberOfReadyRowers == 3;
    }

    public void setFinal(boolean isFinal) {
        this.isFinal = isFinal;
    }

    public int getMetersAdvanced() {
        return metersAdvanced;
    }

    public void updateMetersAdvanced() {
        metersAdvanced += metersRowed;
        metersRowed = 0;
    }

    public String getName() {
        return name;
    }

    public void resetNumberOfReadyRowers() {
        numberOfReadyRowers = 0;
    }

    public boolean rowersHaveRowed() {
        return metersRowed > 0;
    }
}
