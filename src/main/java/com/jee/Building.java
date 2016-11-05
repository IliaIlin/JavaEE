package com.jee;

import com.jee.dto.FloorDTO;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;

public interface Building extends Serializable, Cloneable, Iterable<Integer>, Comparable<Building> {

    int getNumberOfFloors();

    void setNumberOfFloors(int numberOfFloors);

    int[] getNumberOfRoomsOnFloor();

    void setNumberOfRoomsOnFloor(int[] numberOfOfficesOnFloor);

    String getNameOfBuilding();

    void setNameOfBuilding(String nameOfBuilding);

    int countTotalOfRooms();

    FloorDTO findFloorWithMaxRooms();

    FloorDTO findFloorWithMinRooms();

    List<FloorDTO> getAllFloors();

    void output(OutputStream out) throws IOException;

    void write(Writer out);

    Object clone();

    String toString();

    boolean equals(Object object);

    int hashCode();

    Iterator iterator();

    int compareTo(Building comparedBuilding);
}
