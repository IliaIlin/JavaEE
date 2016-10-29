package com.jee.dto;

import java.io.Serializable;

public class FloorDTO implements Serializable {

    private int numberOfRoomsOnFloor;
    private String nameOfBuilding;

    public FloorDTO(int numberOfRoomsOnFloor, String nameOfBuilding) {
        this.numberOfRoomsOnFloor = numberOfRoomsOnFloor;
        this.nameOfBuilding = nameOfBuilding;
    }

    public int getNumberOfRoomsOnFloor() {
        return numberOfRoomsOnFloor;
    }

    public String getNameOfBuilding() {
        return nameOfBuilding;
    }

    public String toString() {
        return new StringBuilder().
                append("nameOfBuilding: ").
                append(nameOfBuilding + "\n").
                append("numberOfRoomsOnFloor: ").
                append(numberOfRoomsOnFloor).
                toString();
    }

    @Override
    public boolean equals(Object object) {
        if (object != null && object instanceof FloorDTO) {
            FloorDTO floor = (FloorDTO) object;
            if (this.getNameOfBuilding().equals(floor.getNameOfBuilding()) &&
                    this.getNumberOfRoomsOnFloor() == floor.getNumberOfRoomsOnFloor()) {
                return true;
            }
            return false;
        }
        return false;
    }
}
