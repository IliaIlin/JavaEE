package com.jee.factory;

import com.jee.Building;
import com.jee.impl.DwellingBuilding;

public class DwellingBuildingFactory implements BuildingFactory {

    public Building createInstance(int numberOfFloors, int[] numberOfFlatsOnFloor, String nameOfBuilding) {
        return new DwellingBuilding(numberOfFloors, numberOfFlatsOnFloor, nameOfBuilding);
    }
}
