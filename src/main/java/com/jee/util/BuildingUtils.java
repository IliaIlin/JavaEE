package com.jee.util;

import com.jee.Building;
import com.jee.dto.FloorDTO;
import com.jee.exceptions.GeneralInputException;
import com.jee.exceptions.NegativeInputNumberException;
import com.jee.factory.BuildingFactory;
import com.jee.factory.DwellingBuildingFactory;
import com.jee.impl.SynchronizedBuilding;
import com.jee.impl.UnmodifiableBuilding;

import java.io.*;
import java.util.Collections;
import java.util.Formatter;
import java.util.List;
import java.util.Scanner;

public class BuildingUtils {

    //DwellingBuildingFactory serves as default value;
    private static BuildingFactory buildingFactory = new DwellingBuildingFactory();

    public static void setBuildingFactory(BuildingFactory factory) {
        buildingFactory = factory;
    }

    public static Building createInstance(int numberOfFloors, int[] numberOfRoomsOnFloor, String nameOfBuilding) {
        return buildingFactory.createInstance(numberOfFloors, numberOfRoomsOnFloor, nameOfBuilding);
    }

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
        return createInstance(numberOfFloors, numberOfRoomsOnFloor, nameOfBuilding);
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
        return createInstance(numberOfFloors, numberOfRoomsOnFloor, nameOfBuilding);
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
        return createInstance(numberOfFloors, numberOfRoomsOnFloor, nameOfBuilding);
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

    public static void sortBuildingsByRoomsTotal(List<Building> buildings) {
        Collections.sort(buildings);
    }

    public static void sortBuildingsByNumberOfFloors(List<Building> buildings) {
        Collections.sort(buildings, new NumberOfFloorsComparator());
    }

    public static void sortBuildingsByNameOfBuilding(List<Building> buildings) {
        Collections.sort(buildings, new NameOfBuildingComparator());
    }

    public static Building unmodifiableBuilding(Building building) {
        return new UnmodifiableBuilding(building);
    }

    public static Building synchronizedBuilding(Building building) {
        return new SynchronizedBuilding(building);
    }
}