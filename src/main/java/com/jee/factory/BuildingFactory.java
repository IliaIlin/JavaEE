package com.jee.factory;

import com.jee.Building;

public interface BuildingFactory {

    Building createInstance(int numberOfFloors, int[] numberOfRoomsOnFloor, String nameOfBuilding);
}
