package com.jee.multithreading;

import com.jee.Building;

import java.util.Random;

public class FirstThreadExtension extends Thread {

    private Building building;
    private Random random;

    public FirstThreadExtension(Building building) {
        this.building = building;
        random = new Random();
        start();
    }

    @Override
    public void run() {
        System.out.println("First THREAD extension is alive!");
        int numberOfFloors = building.getNumberOfFloors();
        int[] numberOfRoomsOnFloor = building.getNumberOfRoomsOnFloor();
        for (int i = 0; i < numberOfFloors; i++) {
            numberOfRoomsOnFloor[i] = random.nextInt(100);
            System.out.println("Write " + numberOfRoomsOnFloor[i] + " to position " + i);
        }
        System.out.println("First THREAD extension is dead!");
    }
}
