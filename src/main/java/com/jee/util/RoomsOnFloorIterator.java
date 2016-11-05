package com.jee.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RoomsOnFloorIterator implements Iterator<Integer> {

    private int[] roomsOnFloors;
    private int sizeOfArray;
    private int cursor;

    public RoomsOnFloorIterator(int[] roomsOnFloors) {
        this.roomsOnFloors = roomsOnFloors;
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
