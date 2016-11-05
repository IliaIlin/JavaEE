package com.jee.util;

import com.jee.Building;
import com.jee.dto.FloorDTO;
import com.jee.impl.DwellingBuilding;
import com.jee.impl.OfficeBuilding;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BuildingUtilsTest {

    private static int testFirstNumberOfFloors;
    private static int testSecondNumberOfFloors;
    private static int [] testFirstNumberRoomsOnFloor;
    private static int [] testSecondNumberRoomsOnFloor;
    private static String testFirstBuildingName;
    private static String testSecondBuildingName;

    private static Building testOfficeBuilding, testDwellingBuilding;
    private static List<Building> testBuildings;

    private FileInputStream fileInputStream;
    private FileOutputStream fileOutputStream;
    private FileReader fileReader;
    private Scanner scanner;

    private final String TEST_FILE_PATH = "src/test/resources/test.txt";

    @BeforeClass
    public static void setUp() throws Exception {
        testFirstNumberOfFloors=17;
        testFirstNumberRoomsOnFloor=new int[]
                {2, 3, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 2};
        testFirstBuildingName="testOfficeBuilding";
        testSecondNumberOfFloors=10;
        testSecondNumberRoomsOnFloor=new int[]
                {1, 20, 20, 20, 20, 20, 20, 20, 20, 10};
        testSecondBuildingName="testDwellingBuilding";

        testOfficeBuilding = new OfficeBuilding(
                testFirstNumberOfFloors,testFirstNumberRoomsOnFloor,testFirstBuildingName);
        testDwellingBuilding = new DwellingBuilding(
                testSecondNumberOfFloors,testSecondNumberRoomsOnFloor,testSecondBuildingName);
        testBuildings = new ArrayList<>();
        testBuildings.add(testDwellingBuilding);
        testBuildings.add(testOfficeBuilding);
    }

    @Before
    public void setUpStreams() throws FileNotFoundException {
        fileInputStream = new FileInputStream(TEST_FILE_PATH);
        fileOutputStream = new FileOutputStream(TEST_FILE_PATH);
        fileReader = new FileReader(TEST_FILE_PATH);
        scanner = new Scanner(fileInputStream);
    }

    @Test
    public void testCreateInstance() {
        Building createdBuilding=BuildingUtils.createInstance(
                testFirstNumberOfFloors,testFirstNumberRoomsOnFloor,testFirstBuildingName);
    assertEquals(testOfficeBuilding,createdBuilding);
    }

    @Test
    public void testInputOutputOfficeBuilding() throws Exception {
        BuildingUtils.outputBuilding(testOfficeBuilding, fileOutputStream);
        Building building = BuildingUtils.inputBuilding(fileInputStream);
        assertEquals(testOfficeBuilding, building);
    }

    @Test
    public void testInputOutputDwellingBuilding() throws Exception {
        BuildingUtils.outputBuilding(testDwellingBuilding, fileOutputStream);
        Building building = BuildingUtils.inputBuilding(fileInputStream);
        assertEquals(testDwellingBuilding, building);
    }

    @Test
    public void testReadWriteOfficeBuilding() throws Exception {
        try (FileWriter fileWriter = new FileWriter(TEST_FILE_PATH)) {
            BuildingUtils.writeBuilding(testOfficeBuilding, fileWriter);
        }
        Building building = BuildingUtils.readBuilding(fileReader);
        assertEquals(testOfficeBuilding, building);
    }

    @Test
    public void testReadWriteDwellingBuilding() throws Exception {
        try (FileWriter fileWriter = new FileWriter(TEST_FILE_PATH)) {
            BuildingUtils.writeBuilding(testDwellingBuilding, fileWriter);
        }
        Building building = BuildingUtils.readBuilding(fileReader);
        assertEquals(testDwellingBuilding, building);
    }

    @Test
    public void testOverloadedReadOfficeBuilding() throws Exception {
        try (FileWriter fileWriter = new FileWriter(TEST_FILE_PATH)) {
            BuildingUtils.writeBuilding(testOfficeBuilding, fileWriter);
        }
        Building building = BuildingUtils.readBuilding(scanner);
        assertEquals(testOfficeBuilding, building);
    }

    @Test
    public void testOverloadedReadDwellingBuilding() throws Exception {
        try (FileWriter fileWriter = new FileWriter(TEST_FILE_PATH)) {
            BuildingUtils.writeBuilding(testDwellingBuilding, fileWriter);
        }
        Building building = BuildingUtils.readBuilding(scanner);
        assertEquals(testDwellingBuilding, building);
    }

    @Test
    @Ignore
    public void testPrintFloorDTOListToFile() throws Exception {
        List<FloorDTO> listToWrite = testOfficeBuilding.getAllFloors();
        try (FileWriter fileWriter = new FileWriter(TEST_FILE_PATH)) {
            BuildingUtils.printFloorDTOListToFile(listToWrite, fileWriter);
        }
        scanner.close();
        scanner = new Scanner(fileReader);
        int listSize = scanner.nextInt();
        List<FloorDTO> listToRead = new ArrayList<>();
        for (int i = 0; i < listSize; i++) {
            String nameOfBuilding = scanner.next();
            int numberOfFloors = scanner.nextInt();
            listToRead.add(new FloorDTO(numberOfFloors, nameOfBuilding));
        }
        assertTrue(listToWrite.equals(listToRead));
    }

    @Test
    public void testSerializeDeserializeOfficeBuilding() throws Exception {
        BuildingUtils.serializeBuilding(testOfficeBuilding, TEST_FILE_PATH);
        Building building = BuildingUtils.deserializeBuilding(TEST_FILE_PATH);
        assertEquals(testOfficeBuilding, building);
    }

    @Test
    public void testSerializeDeserializeDwellingBuilding() throws Exception {
        BuildingUtils.serializeBuilding(testDwellingBuilding, TEST_FILE_PATH);
        Building building = BuildingUtils.deserializeBuilding(TEST_FILE_PATH);
        assertEquals(testDwellingBuilding, building);
    }

    @Test
    public void testSortBuildingsByRoomsTotal() {
        BuildingUtils.sortBuildingsByRoomsTotal(testBuildings);
        assertEquals(testOfficeBuilding, testBuildings.get(0));
        assertEquals(testDwellingBuilding, testBuildings.get(1));
    }

    @Test
    public void testSortBuildingsByNumberOfFloors() {
        BuildingUtils.sortBuildingsByNumberOfFloors(testBuildings);
        assertEquals(testDwellingBuilding, testBuildings.get(0));
        assertEquals(testOfficeBuilding, testBuildings.get(1));
    }

    @Test
    public void testSortBuildingsByNameOfBuilding() {
        BuildingUtils.sortBuildingsByNameOfBuilding(testBuildings);
        assertEquals(testDwellingBuilding, testBuildings.get(0));
        assertEquals(testOfficeBuilding, testBuildings.get(1));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testUnmodifiableSetNumberOfFloors() {
        Building building = BuildingUtils.unmodifiableBuilding(testOfficeBuilding);
        building.setNumberOfFloors(5);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testUnmodifiableSetNumberOfRoomsOnFloor() {
        Building building = BuildingUtils.unmodifiableBuilding(testOfficeBuilding);
        building.setNumberOfRoomsOnFloor(new int[]{1, 2, 3});
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testUnmodifiableSetNameOfBuilding() {
        Building building = BuildingUtils.unmodifiableBuilding(testOfficeBuilding);
        building.setNameOfBuilding("test");
    }
}