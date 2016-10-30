package com.jee.util;

import com.jee.Building;
import com.jee.dto.FloorDTO;
import com.jee.exceptions.GeneralInputException;
import com.jee.exceptions.NegativeInputNumberException;
import com.jee.exceptions.WrongBuildingTypeException;
import com.jee.impl.DwellingBuilding;
import com.jee.impl.OfficeBuilding;
import com.jee.util.NameOfBuildingComparator;
import com.jee.util.NumberOfFloorsComparator;

import java.io.*;
import java.util.Arrays;
import java.util.Formatter;
import java.util.List;
import java.util.Scanner;

public class BuildingUtils {

    public static void outputBuilding(Building building, OutputStream out) throws IOException {
        building.output(out);
    }

    public static Building inputBuilding(InputStream in) throws IOException {
        DataInputStream dataInputStream = new DataInputStream(in);
        String nameOfBuilding = dataInputStream.readUTF();
        int numberOfFloors = dataInputStream.readInt();
        int[] numberOfRoomsOnFloor = new int[numberOfFloors];
        for (int i = 0; i < numberOfFloors; i++) {
            numberOfRoomsOnFloor[i] = dataInputStream.readInt();
        }
        try {
            String buildingType = dataInputStream.readUTF().toLowerCase();
            if (buildingType.equals("office")) {
                return new OfficeBuilding(numberOfFloors, numberOfRoomsOnFloor, nameOfBuilding);
            } else if (buildingType.equals("dwelling")) {
                return new DwellingBuilding(numberOfFloors, numberOfRoomsOnFloor, nameOfBuilding);
            } else throw new WrongBuildingTypeException("com.jee.Building type should be office or dwelling");
        } catch (WrongBuildingTypeException exception) {
            throw new IOException(exception.getMessage());
        }
    }

    public static void writeBuilding(Building building, Writer out) {
        building.write(out);
    }

    public static Building readBuilding(Reader in) throws IOException {
        StreamTokenizer streamTokenizer = new StreamTokenizer(in);
        streamTokenizer.nextToken();
        String nameOfBuilding = streamTokenizer.sval;
        streamTokenizer.nextToken();
        int numberOfFloors = (int) streamTokenizer.nval;
        int[] numberOfRoomsOnFloor = new int[numberOfFloors];
        for (int i = 0; i < numberOfFloors; i++) {
            streamTokenizer.nextToken();
            numberOfRoomsOnFloor[i] = (int) streamTokenizer.nval;
        }
        streamTokenizer.nextToken();
        try {
            String buildingType = streamTokenizer.sval.toLowerCase();
            if (buildingType.equals("office")) {
                return new OfficeBuilding(numberOfFloors, numberOfRoomsOnFloor, nameOfBuilding);
            } else if (buildingType.equals("dwelling")) {
                return new DwellingBuilding(numberOfFloors, numberOfRoomsOnFloor, nameOfBuilding);
            } else throw new WrongBuildingTypeException("com.jee.Building type should be office or dwelling");
        } catch (WrongBuildingTypeException exception) {
            throw new IOException(exception.getMessage());
        }
    }

    public static Building readBuilding(Scanner in) {
        System.out.println("Enter name of building: ");
        String nameOfBuilding = in.next();
        System.out.println("Enter number of floors: ");
        int numberOfFloors = in.nextInt();
        if (numberOfFloors < 0) throw new NegativeInputNumberException();
        int[] numberOfRoomsOnFloor = new int[numberOfFloors];
        for (int j = 0; j < numberOfFloors; j++) {
            System.out.println("Enter number of rooms on " + j + "-th floor");
            numberOfRoomsOnFloor[j] = in.nextInt();
            if (numberOfRoomsOnFloor[j] < 0) throw new NegativeInputNumberException(
                    "Number of rooms on each floor should be positive");
        }
        System.out.println("Enter type of building (office of dwelling): ");
        String buildingType = in.next().toLowerCase();
        if (buildingType.equals("office"))
            return new OfficeBuilding(numberOfFloors, numberOfRoomsOnFloor, nameOfBuilding);
        else if (buildingType.equals("dwelling"))
            return new DwellingBuilding(numberOfFloors, numberOfRoomsOnFloor, nameOfBuilding);
        else throw new WrongBuildingTypeException("com.jee.Building type should be office or dwelling");
    }

    public static void printFloorDTOListToFile(List<FloorDTO> floors, Writer out) {
        Formatter formatter = new Formatter(out);
        formatter.format("%d %n", floors.size());
        floors.stream().forEach((floor) ->
                formatter.format("%s %d %n", floor.getNameOfBuilding(), floor.getNumberOfRoomsOnFloor()));
    }

    public static void serializeBuilding(Building building, String fileName) throws GeneralInputException {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            objectOutputStream.writeObject(building);
        } catch (FileNotFoundException exception) {
            throw new GeneralInputException("File is not found");
        } catch (IOException exception) {
            throw new GeneralInputException("Serialization failed");
        }
    }

    public static Building deserializeBuilding(String fileName) throws GeneralInputException {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            return (Building) objectInputStream.readObject();
        } catch (FileNotFoundException exception) {
            throw new GeneralInputException("File is not found");
        } catch (IOException exception) {
            throw new GeneralInputException("Deserialization failed");
        } catch (ClassNotFoundException exception) {
            throw new GeneralInputException("Class is not found");
        }
    }

    public static void sortBuildingsByRoomsTotal(Building[] buildings) {
        Arrays.sort(buildings);
    }

    public static void sortBuildingsByNumberOfFloors(Building[] buildings) {
        Arrays.sort(buildings, new NumberOfFloorsComparator());
    }

    public static void sortBuildingsByNameOfBuilding(Building[] buildings) {
        Arrays.sort(buildings, new NameOfBuildingComparator());
    }
}