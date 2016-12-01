package com.jee.impl;

import com.jee.Building;
import com.jee.dto.FloorDTO;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;

public class SynchronizedBuilding implements Building {

    private Building synchronizedBuilding;

    public SynchronizedBuilding(Building unsyncronizedBuilding) {
        synchronizedBuilding = unsyncronizedBuilding;
    }

    public synchronized int getNumberOfFloors() {
        return synchronizedBuilding.getNumberOfFloors();
    }

    public synchronized void setNumberOfFloors(int numberOfFloors) {
        synchronizedBuilding.setNumberOfFloors(numberOfFloors);
    }

    public synchronized int[] getNumberOfRoomsOnFloor() {
        return synchronizedBuilding.getNumberOfRoomsOnFloor();
    }

    public synchronized void setNumberOfRoomsOnFloor(int[] numberOfOfficesOnFloor) {
        synchronizedBuilding.setNumberOfRoomsOnFloor(numberOfOfficesOnFloor);
    }

    public synchronized String getNameOfBuilding() {
        return synchronizedBuilding.getNameOfBuilding();
    }

    public synchronized void setNameOfBuilding(String nameOfBuilding) {
        synchronizedBuilding.setNameOfBuilding(nameOfBuilding);
    }

    public synchronized int countTotalOfRooms() {
        return synchronizedBuilding.countTotalOfRooms();
    }

    public synchronized FloorDTO findFloorWithMaxRooms() {
        return synchronizedBuilding.findFloorWithMaxRooms();
    }

    public synchronized FloorDTO findFloorWithMinRooms() {
        return synchronizedBuilding.findFloorWithMinRooms();
    }

    public synchronized List<FloorDTO> getAllFloors() {
        return synchronizedBuilding.getAllFloors();
    }

    public synchronized void output(OutputStream out) throws IOException {
        synchronizedBuilding.output(out);
    }

    public synchronized void write(Writer out) {
        synchronizedBuilding.write(out);
    }

    @Override
    public synchronized Object clone() {
        return synchronizedBuilding.clone();
    }

    @Override
    public synchronized String toString() {
        return synchronizedBuilding.toString();
    }

    @Override
    public synchronized boolean equals(Object object) {
        return synchronizedBuilding.equals(object);
    }

    @Override
    public synchronized int hashCode() {
        return synchronizedBuilding.hashCode();
    }

    public synchronized Iterator iterator() {
        return synchronizedBuilding.iterator();
    }

    public synchronized int compareTo(Building comparedBuilding) {
        return synchronizedBuilding.compareTo(comparedBuilding);
    }
}
