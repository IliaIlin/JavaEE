package com.jee.multithreading;

import com.jee.Building;

import java.util.Random;

public class FirstRunnableThread implements Runnable {

    private Thread internalThread;
    private Building building;
    private BuildingKeeper buildingKeeper;
    private Random random;

    public FirstRunnableThread(BuildingKeeper buildingKeeper) {
        this.building = buildingKeeper.getBuilding();
        this.buildingKeeper = buildingKeeper;
        random = new Random();
        internalThread = new Thread(this);
        internalThread.start();
    }

    public void run() {
        System.out.println("First Runnable THREAD is alive!");
        int numberOfFloors = building.getNumberOfFloors();
        for (int i = 0; i < numberOfFloors; i++) {
            try {
                buildingKeeper.write(random.nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        System.out.println("First Runnable THREAD is dead!");
    }
}
