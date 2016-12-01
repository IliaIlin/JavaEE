package com.jee.multithreading;

import com.jee.Building;

public class SecondRunnableThread implements Runnable {

    private Thread internalThread;
    private Building building;
    private BuildingKeeper buildingKeeper;

    public SecondRunnableThread(BuildingKeeper buildingKeeper) {
        this.building = buildingKeeper.getBuilding();
        this.buildingKeeper = buildingKeeper;
        internalThread = new Thread(this);
        internalThread.start();
    }

    public void run() {
        System.out.println("Second Runnable THREAD is alive!");
        int numberOfFloors = building.getNumberOfFloors();
        for (int i = 0; i < numberOfFloors; i++) {
            try {
                buildingKeeper.read();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Second Runnable THREAD is dead!");
    }
}
