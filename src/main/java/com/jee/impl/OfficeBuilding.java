package com.jee.impl;


import com.jee.Building;
import com.jee.dto.FloorDTO;

import java.io.*;
import java.util.Arrays;
import java.util.Formatter;
import java.util.List;
import java.util.stream.Collectors;

public class OfficeBuilding implements Building, Serializable, Cloneable {

    private int numberOfFloors;
    private int[] numberOfOfficesOnFloor;
    private String nameOfBuilding;


    public OfficeBuilding() {

    }

    public OfficeBuilding(int numberOfFloors, int[] numberOfOfficesOnFloor, String nameOfBuilding) {
        this.numberOfFloors = numberOfFloors;
        this.numberOfOfficesOnFloor = numberOfOfficesOnFloor;
        this.nameOfBuilding = nameOfBuilding;
    }

    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    public void setNumberOfFloors(int numberOfFloors) {
        this.numberOfFloors = numberOfFloors;
    }

    public int[] getNumberOfRoomsOnFloor() {
        return numberOfOfficesOnFloor;
    }

    public void setNumberOfRoomsOnFloor(int[] numberOfOfficesOnFloor) {
        this.numberOfOfficesOnFloor = numberOfOfficesOnFloor;
    }

    public String getNameOfBuilding() {
        return nameOfBuilding;
    }


    public void setNameOfBuilding(String nameOfBuilding) {
        this.nameOfBuilding = nameOfBuilding;
    }

    public int countTotalOfRooms() {
        return Arrays.stream(numberOfOfficesOnFloor).sum();
    }

    public FloorDTO findFloorWithMaxRooms() {
        int maxValue = Arrays.stream(numberOfOfficesOnFloor).max().getAsInt();
        int maxIndex = Arrays.binarySearch(numberOfOfficesOnFloor, maxValue);
        return new FloorDTO(numberOfOfficesOnFloor[maxIndex], nameOfBuilding);
    }

    public FloorDTO findFloorWithMinRooms() {
        int minValue = Arrays.stream(numberOfOfficesOnFloor).min().getAsInt();
        int minIndex = Arrays.binarySearch(numberOfOfficesOnFloor, minValue);
        return new FloorDTO(numberOfOfficesOnFloor[minIndex], nameOfBuilding);
    }

    public List<FloorDTO> getAllFloors() {
        return Arrays.stream(numberOfOfficesOnFloor).
                mapToObj(i -> new FloorDTO(i, this.nameOfBuilding)).
                collect(Collectors.toList());
    }

    public void output(OutputStream out) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(out);
        dataOutputStream.writeUTF(nameOfBuilding);
        dataOutputStream.writeInt(numberOfFloors);
        for (int i = 0; i < numberOfFloors; i++) {
            dataOutputStream.writeInt(numberOfOfficesOnFloor[i]);
        }
        dataOutputStream.writeUTF("office");
    }

    public void write(Writer out) {
        Formatter formatter = new Formatter(out);
        formatter.format("%s %d ", nameOfBuilding, numberOfFloors);
        Arrays.stream(numberOfOfficesOnFloor).forEach(i -> formatter.format("%d ", i));
        formatter.format("%s %n", "office");
    }

    public Object clone() {
        OfficeBuilding result = null;
        try {
            result = (OfficeBuilding) super.clone();
            result.numberOfOfficesOnFloor = numberOfOfficesOnFloor.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder().
                append("numberOfFloors: ").
                append(numberOfFloors + "\n").
                append("numberOfFlatsOnFloor: ").
                append(Arrays.toString(numberOfOfficesOnFloor) + "\n").
                append("nameOfBuilding: ").
                append(nameOfBuilding).
                toString();
    }


    @Override
    public boolean equals(Object object) {
        if (object != null && object instanceof Building) {
            Building building = (Building) object;
            if (this.getNameOfBuilding().equals(building.getNameOfBuilding()) &&
                    this.getNumberOfFloors() == building.getNumberOfFloors()) {
                return Arrays.equals(this.getNumberOfRoomsOnFloor(), building.getNumberOfRoomsOnFloor());
            }
            return false;
        } else return false;
    }

    @Override
    public int hashCode() {
        int result = numberOfFloors;
        result *= Arrays.stream(numberOfOfficesOnFloor).reduce((i1, i2) -> 19 * i1 + i2).getAsInt();
        return result * nameOfBuilding.chars().reduce((c1, c2) -> 19 * c1 + c2).getAsInt();
    }
}
