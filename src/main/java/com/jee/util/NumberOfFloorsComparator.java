package com.jee.util;

import com.jee.Building;

import java.util.Comparator;

@Deprecated
public class NumberOfFloorsComparator implements Comparator<Building> {

    public int compare(Building o1, Building o2) {
        return o1.getNumberOfFloors() - o2.getNumberOfFloors();
    }
}
