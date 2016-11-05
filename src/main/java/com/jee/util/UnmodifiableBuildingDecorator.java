package com.jee.util;

import com.jee.Building;
import com.jee.dto.FloorDTO;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;

public class UnmodifiableBuildingDecorator implements Building {

    private Building unmodifiableBuilding;

    public UnmodifiableBuildingDecorator(Building modifiableBuilding) {
        this.unmodifiableBuilding = modifiableBuilding;
    }

    @Override
    public int getNumberOfFloors() {
        return unmodifiableBuilding.getNumberOfFloors();
    }

    @Override
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

    public Object clone() {
        return unmodifiableBuilding.clone();
    }

    public String toString() {
        return unmodifiableBuilding.toString();
    }

    public boolean equals(Object object) {
        return unmodifiableBuilding.equals(object);
    }

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
