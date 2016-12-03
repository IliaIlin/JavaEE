package com.jee.util;

import com.jee.Building;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RoomsOnFloorIterator implements Iterator<Integer> {

    private int[] roomsOnFloors;
    private int sizeOfArray;
    private int cursor;

    public RoomsOnFloorIterator(Building building) {
        this.roomsOnFloors = building.getNumberOfRoomsOnFloor();
        sizeOfArray = roomsOnFloors.length;
        cursor = 0;

    }

    public boolean hasNext() {
        if (cursor < sizeOfArray) {
            return true;
        }
        return false;
    }

    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        } else {
            return roomsOnFloors[cursor++];
        }
    }
}
