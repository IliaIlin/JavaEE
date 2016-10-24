package com.jee.impl;

import com.jee.Building;
import com.jee.dto.FloorDTO;

import java.io.*;
import java.util.Arrays;
import java.util.Formatter;
import java.util.List;
import java.util.stream.Collectors;

public class DwellingBuilding implements Building, Serializable, Cloneable {

    private int numberOfFloors;
    private int[] numberOfFlatsOnFloor;
    private String nameOfBuilding;

    public DwellingBuilding() {

    }

    public DwellingBuilding(int numberOfFloors, int[] numberOfFlatsOnFloor, String nameOfBuilding) {
        this.numberOfFloors = numberOfFloors;
        this.numberOfFlatsOnFloor = numberOfFlatsOnFloor;
        this.nameOfBuilding = nameOfBuilding;
    }

    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    public void setNumberOfFloors(int numberOfFloors) {
        this.numberOfFloors = numberOfFloors;
    }

    public int[] getNumberOfRoomsOnFloor() {
        return numberOfFlatsOnFloor;
    }

    public void setNumberOfRoomsOnFloor(int[] numberOfFlatsOnFloor) {
        this.numberOfFlatsOnFloor = numberOfFlatsOnFloor;
    }

    public String getNameOfBuilding() {
        return nameOfBuilding;
    }

    public void setNameOfBuilding(String nameOfBuilding) {
        this.nameOfBuilding = nameOfBuilding;
    }

    public int countTotalOfRooms() {
        return Arrays.stream(numberOfFlatsOnFloor).sum();
    }

    public FloorDTO findFloorWithMaxRooms() {
        int maxIndex = Arrays.stream(numberOfFlatsOnFloor).max().getAsInt();
        return new FloorDTO(numberOfFlatsOnFloor[maxIndex], nameOfBuilding);
    }

    public FloorDTO findFloorWithMinRooms() {
        int minIndex = Arrays.stream(numberOfFlatsOnFloor).min().getAsInt();
        return new FloorDTO(numberOfFlatsOnFloor[minIndex], nameOfBuilding);
    }

    public List<FloorDTO> getAllFloors(){
        return Arrays.stream(numberOfFlatsOnFloor).
                mapToObj(i -> new FloorDTO(i, this.nameOfBuilding)).
                collect(Collectors.toList());
    }

    public void output(OutputStream out) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(out);
        dataOutputStream.writeUTF(nameOfBuilding);
        dataOutputStream.writeInt(numberOfFloors);
        for (Integer i : numberOfFlatsOnFloor) {
            dataOutputStream.writeInt(i);
        }
        dataOutputStream.writeUTF("dwelling");
    }

    public void write(Writer out) {
        Formatter formatter = new Formatter(out);
        formatter.format("%s %d ", nameOfBuilding, numberOfFloors);
        Arrays.stream(numberOfFlatsOnFloor).forEach(i -> formatter.format("%d ", i));
        formatter.format("%s %n", "dwelling");
    }

    public Object clone() {
        DwellingBuilding result = null;
        try {
            result = (DwellingBuilding) super.clone();
            result.numberOfFlatsOnFloor = numberOfFlatsOnFloor.clone();
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
                append(Arrays.toString(numberOfFlatsOnFloor) + "\n").
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
        result ^= Arrays.stream(numberOfFlatsOnFloor).reduce((i1, i2) -> i1 ^ i2).getAsInt();
        return result ^ nameOfBuilding.chars().reduce((c1, c2) -> c1 ^ c2).getAsInt();
    }
}
