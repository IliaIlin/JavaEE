package com.jee;

import com.jee.dto.FloorDTO;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.List;

public interface Building {

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
}
