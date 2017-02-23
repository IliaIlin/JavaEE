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
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BuildingUtilsTest {

    private static int testFirstNumberOfFloors;
    private static int testSecondNumberOfFloors;
    private static int[] testFirstNumberRoomsOnFloor;
    private static int[] testSecondNumberRoomsOnFloor;
    private static String testFirstBuildingName;
    private static String testSecondBuildingName;

    private static Building testBuilding, testBuilding2;
    private static List<Building> testBuildings;
    private final String TEST_FILE_PATH = "src/test/resources/test.txt";
    private FileInputStream fileInputStream;
    private FileOutputStream fileOutputStream;
    private FileReader fileReader;
    private Scanner scanner;

    @BeforeClass
    public static void setUp() throws Exception
    {
        testFirstNumberOfFloors = 17;
        testFirstNumberRoomsOnFloor = new int[]
                {2, 3, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 2};
        testFirstBuildingName = "testBuilding";
        testSecondNumberOfFloors = 10;
        testSecondNumberRoomsOnFloor = new int[]
                {1, 20, 20, 20, 20, 20, 20, 20, 20, 10};
        testSecondBuildingName = "testBuilding2";

        testBuilding = new OfficeBuilding(
                testFirstNumberOfFloors, testFirstNumberRoomsOnFloor, testFirstBuildingName);
        testBuilding2 = new DwellingBuilding(
                testSecondNumberOfFloors, testSecondNumberRoomsOnFloor, testSecondBuildingName);
        testBuildings = new ArrayList<>();
        testBuildings.add(testBuilding2);
        testBuildings.add(testBuilding);
    }

    @Before
    public void setUpStreams() throws FileNotFoundException
    {
        fileInputStream = new FileInputStream(TEST_FILE_PATH);
        fileOutputStream = new FileOutputStream(TEST_FILE_PATH);
        fileReader = new FileReader(TEST_FILE_PATH);
        scanner = new Scanner(fileInputStream);
    }

    @Test
    public void testCreateInstance()
    {
        Building createdBuilding = BuildingUtils.createInstance(
                testFirstNumberOfFloors, testFirstNumberRoomsOnFloor, testFirstBuildingName);
        assertEquals(testBuilding, createdBuilding);
    }

    @Test
    public void testCreateInstanceByClass() throws InvocationTargetException,
            NoSuchMethodException, InstantiationException, IllegalAccessException
    {
        Building createdBuilding = BuildingUtils.createInstance
                (testFirstNumberOfFloors,
                        testFirstNumberRoomsOnFloor, testFirstBuildingName,
                        OfficeBuilding.class);
        assertEquals(testBuilding, createdBuilding);
    }

    @Test
    public void testInputOutput() throws Exception
    {
        BuildingUtils.outputBuilding(testBuilding, fileOutputStream);
        Building building = BuildingUtils.inputBuilding(fileInputStream);
        assertEquals(testBuilding, building);
    }

    @Test
    public void testInputOutputByClass() throws Exception
    {
        BuildingUtils.outputBuilding(testBuilding, fileOutputStream);
        Building inputByOfficeBuildingClass = BuildingUtils.inputBuilding(fileInputStream, OfficeBuilding.class);
        assertEquals(testBuilding,inputByOfficeBuildingClass);
    }

    @Test
    public void testReadWrite() throws Exception
    {
        try (FileWriter fileWriter = new FileWriter(TEST_FILE_PATH)) {
            BuildingUtils.writeBuilding(testBuilding, fileWriter);
        }
        Building building = BuildingUtils.readBuilding(fileReader);
        assertEquals(testBuilding, building);
    }

    @Test
    public void testOverloadedRead() throws Exception
    {
        try (FileWriter fileWriter = new FileWriter(TEST_FILE_PATH)) {
            BuildingUtils.writeBuilding(testBuilding, fileWriter);
        }
        Building building = BuildingUtils.readBuilding(scanner);
        assertEquals(testBuilding, building);
    }

    @Test
    @Ignore
    public void testPrintFloorDTOListToFile() throws Exception
    {
        List<FloorDTO> listToWrite = testBuilding.getAllFloors();
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
    public void testSerializeDeserialize() throws Exception
    {
        BuildingUtils.serializeBuilding(testBuilding, TEST_FILE_PATH);
        Building building = BuildingUtils.deserializeBuilding(TEST_FILE_PATH);
        assertEquals(testBuilding, building);
    }

    @Test
    public void testSortBuildingsByRoomsTotal()
    {
        BuildingUtils.sortBuildingsByRoomsTotal(testBuildings);
        assertEquals(testBuilding, testBuildings.get(0));
        assertEquals(testBuilding2, testBuildings.get(1));
    }

    @Test
    public void testSortBuildingsByNumberOfFloors()
    {
        BuildingUtils.sortBuildingsByNumberOfFloors(testBuildings);
        assertEquals(testBuilding2, testBuildings.get(0));
        assertEquals(testBuilding, testBuildings.get(1));
    }

    @Test
    public void testSortBuildingsByNameOfBuilding()
    {
        BuildingUtils.sortBuildingsByNameOfBuilding(testBuildings);
        assertEquals(testBuilding, testBuildings.get(0));
        assertEquals(testBuilding2, testBuildings.get(1));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testUnmodifiableSetNumberOfFloors()
    {
        Building building = BuildingUtils.unmodifiableBuilding(testBuilding);
        building.setNumberOfFloors(5);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testUnmodifiableSetNumberOfRoomsOnFloor()
    {
        Building building = BuildingUtils.unmodifiableBuilding(testBuilding);
        building.setNumberOfRoomsOnFloor(new int[]{1, 2, 3});
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testUnmodifiableSetNameOfBuilding()
    {
        Building building = BuildingUtils.unmodifiableBuilding(testBuilding);
        building.setNameOfBuilding("test");
    }
}