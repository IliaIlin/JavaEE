package com.jee.impl;

import com.jee.Building;
import com.jee.dto.FloorDTO;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;

public class UnmodifiableBuilding implements Building {

    private Building unmodifiableBuilding;

    public UnmodifiableBuilding(Building modifiableBuilding) {
        unmodifiableBuilding = modifiableBuilding;
    }

    public int getNumberOfFloors() {
        return unmodifiableBuilding.getNumberOfFloors();
    }

    public void setNumberOfFloors(int numberOfFloors) {
        throw new UnsupportedOperationException();
    }

    public int[] getNumberOfRoomsOnFloor() {
        return unmodifiableBuilding.getNumberOfRoomsOnFloor();
    }

    public void setNumberOfRoomsOnFloor(int[] numberOfOfficesOnFloor) {
        throw new UnsupportedOperationException();
    }

    public String getNameOfBuilding() {
        return unmodifiableBuilding.getNameOfBuilding();
    }

    public void setNameOfBuilding(String nameOfBuilding) {
        throw new UnsupportedOperationException();
    }

    public int countTotalOfRooms() {
        return unmodifiableBuilding.countTotalOfRooms();
    }

    public FloorDTO findFloorWithMaxRooms() {
        return unmodifiableBuilding.findFloorWithMaxRooms();
    }

    public FloorDTO findFloorWithMinRooms() {
        return unmodifiableBuilding.findFloorWithMinRooms();
    }

    public List<FloorDTO> getAllFloors() {
        return unmodifiableBuilding.getAllFloors();
    }

    public void output(OutputStream out) throws IOException {
        unmodifiableBuilding.output(out);
    }

    public void write(Writer out) {
        unmodifiableBuilding.write(out);
    }

    @Override
    public Object clone() {
        return unmodifiableBuilding.clone();
    }

    @Override
    public String toString() {
        return unmodifiableBuilding.toString();
    }

    @Override
    public boolean equals(Object object) {
        return unmodifiableBuilding.equals(object);
    }

    @Override
    public int hashCode() {
        return unmodifiableBuilding.hashCode();
    }

    public int compareTo(Building comparedUnmodifiableBulding) {
        return unmodifiableBuilding.compareTo(comparedUnmodifiableBulding);
    }

    public Iterator iterator() {
        return unmodifiableBuilding.iterator();
    }
}
