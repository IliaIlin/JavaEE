package com.jee.multithreading;

import com.jee.Building;

public class SecondThreadExtension extends Thread {

    private Building building;

    public SecondThreadExtension(Building building) {
        this.building = building;
        start();
    }

    @Override
    public void run() {
        System.out.println("Second THREAD extension is alive!");
        int numberOfFloors = building.getNumberOfFloors();
        for (int i = 0; i < numberOfFloors; i++) {
            System.out.println("Read " + building.getNumberOfRoomsOnFloor()[i] + " from position " + i);
        }
        System.out.println("Second THREAD extension is dead!");
    }
}
