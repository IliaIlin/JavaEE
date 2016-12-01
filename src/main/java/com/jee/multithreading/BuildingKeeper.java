package com.jee.multithreading;

import com.jee.Building;

public class BuildingKeeper {

    private Building building;
    private volatile int current = 0;
    private Object lock = new Object();
    private boolean set = false;

    public BuildingKeeper(Building building) {
        this.building = building;
    }

    public Building getBuilding() {
        return building;
    }

    public int read() throws InterruptedException {
        int val;
        synchronized (lock) {
            if (!canRead()) throw new InterruptedException();
            while (!set)
                lock.wait();
            val = building.getNumberOfRoomsOnFloor()[current++];
            System.out.println("Read: " + val);
            set = false;
            lock.notifyAll();
        }
        return val;
    }

    public void write(int val) throws InterruptedException {
        synchronized (lock) {
            if (!canWrite()) throw new InterruptedException();
            while (set)
                lock.wait();
            int[] numberOfRoomsOnFloor = building.getNumberOfRoomsOnFloor();
            numberOfRoomsOnFloor[current] = val;
            System.out.println("Write: " + val);
            set = true;
            lock.notifyAll();
        }
    }

    public boolean canRead() {
        return current < building.getNumberOfFloors();
    }

    public boolean canWrite() {
        return (!set && current < building.getNumberOfFloors()) ||
                (set && current < building.getNumberOfFloors() - 1);

    }
}
