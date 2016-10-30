package com.jee.util;

import com.jee.Building;

import java.util.Comparator;

public class NameOfBuildingComparator implements Comparator<Building> {

    public int compare(Building o1, Building o2) {
        return o1.getNameOfBuilding().compareTo(o2.getNameOfBuilding());
    }
}
