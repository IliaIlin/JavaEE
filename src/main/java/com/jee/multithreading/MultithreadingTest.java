package com.jee.multithreading;

import com.jee.Building;
import com.jee.impl.OfficeBuilding;
import com.jee.impl.SynchronizedBuilding;

public class MultithreadingTest {

    public static void main(String[] args) {
        Building testBuilding = new OfficeBuilding(30, new int[30], "sharedBuilding");
        Building synchronizedBuilding = new SynchronizedBuilding(testBuilding);
        BuildingKeeper buildingKeeper = new BuildingKeeper(testBuilding);

        System.out.println("Test Threads Extension (unsynchronized) \n");
        Thread firstThread = new FirstThreadExtension(testBuilding);
        Thread secondThread = new SecondThreadExtension(testBuilding);
        try {
            firstThread.join();
            secondThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n Test Threads Extension (synchronized) \n");
        Thread firstThreadSync = new FirstThreadExtension(synchronizedBuilding);
        Thread secondThreadSync = new SecondThreadExtension(synchronizedBuilding);
        try {
            firstThreadSync.join();
            secondThreadSync.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Test Runnable Threads and Keeper");
        Runnable firstRunnableThread = new FirstRunnableThread(buildingKeeper);
        Runnable secondRunnableThread = new SecondRunnableThread(buildingKeeper);
    }
}
