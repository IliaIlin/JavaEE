package com.jee;

import com.jee.dto.FloorDTO;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.Writer;
import java.util.List;
import java.util.function.Consumer;

public interface Building extends Serializable, Cloneable, Iterable, Comparable {

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

    int compareTo(Object o);
}
