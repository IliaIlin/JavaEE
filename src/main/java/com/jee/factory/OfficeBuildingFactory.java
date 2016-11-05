package com.jee.factory;

import com.jee.Building;
import com.jee.impl.OfficeBuilding;

public class OfficeBuildingFactory implements BuildingFactory {

    public Building createInstance(int numberOfFloors, int[] numberOfOfficesOnFloor, String nameOfBuilding) {
        return new OfficeBuilding(numberOfFloors,numberOfOfficesOnFloor,nameOfBuilding);
    }
}
