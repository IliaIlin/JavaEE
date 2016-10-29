package com.jee.impl;

import com.jee.dto.FloorDTO;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class DwellingBuildingTest {

    private static int testNumberOfFloors;
    private static int testWrongNumberOfFloors;
    private static int[] testNumberRoomsOnFloor;
    private static int[] testWrongNumberRoomsOnFloor;
    private static String testNameOfBuilding;
    private static String testWrongNameOfBuilding;
    private static int testTotalRoomsCount;
    private static FloorDTO testFloorWithMaxRooms;
    private static FloorDTO testFloorWithMinRooms;
    private static List<FloorDTO> testAllFloors;

    private static DwellingBuilding testDwellingBuilding;


    @BeforeClass
    public static void setUp() throws Exception {
        testNumberOfFloors = 3;
        testWrongNumberOfFloors = 1;
        testNumberRoomsOnFloor = new int[]{1, 2, 3};
        testWrongNumberRoomsOnFloor = new int[]{3, 2, 1};
        testNameOfBuilding = "testDwellingBuilding";
        testWrongNameOfBuilding = "testWrongDwellingBuilding";

        testTotalRoomsCount = Arrays.stream(testNumberRoomsOnFloor).sum();
        testFloorWithMaxRooms = new FloorDTO(3, testNameOfBuilding);
        testFloorWithMinRooms = new FloorDTO(1, testNameOfBuilding);

        testAllFloors = new ArrayList<>();
        testAllFloors.add(testFloorWithMinRooms);
        testAllFloors.add(new FloorDTO(2, testNameOfBuilding));
        testAllFloors.add(testFloorWithMaxRooms);

        testDwellingBuilding = new DwellingBuilding(testNumberOfFloors,
                testNumberRoomsOnFloor, testNameOfBuilding);
    }

    @Test
    public void testCountTotalOfRooms() throws Exception {
        assertEquals(testTotalRoomsCount, testDwellingBuilding.countTotalOfRooms());
    }

    @Test
    public void testFindFloorWithMaxRooms() throws Exception {
        assertEquals(testFloorWithMaxRooms, testDwellingBuilding.findFloorWithMaxRooms());
    }

    @Test
    public void testFindFloorWithMinRooms() throws Exception {
        assertEquals(testFloorWithMinRooms, testDwellingBuilding.findFloorWithMinRooms());
    }

    @Test
    public void testGetAllFloors() throws Exception {
        assertEquals(testAllFloors, testDwellingBuilding.getAllFloors());
    }

    @Test
    public void testClone() throws Exception {
        assertEquals(testDwellingBuilding, testDwellingBuilding.clone());
    }

    @Test
    public void testEquals() throws Exception {
        DwellingBuilding clonedTestDwellingBuilding = (DwellingBuilding) testDwellingBuilding.clone();
        assertEquals(testDwellingBuilding, clonedTestDwellingBuilding);

        clonedTestDwellingBuilding.setNumberOfFloors(testWrongNumberOfFloors);
        assertNotEquals(testDwellingBuilding, clonedTestDwellingBuilding);

        clonedTestDwellingBuilding.setNumberOfFloors(testNumberOfFloors);
        clonedTestDwellingBuilding.setNumberOfRoomsOnFloor(testWrongNumberRoomsOnFloor);
        assertNotEquals(testDwellingBuilding, clonedTestDwellingBuilding);

        clonedTestDwellingBuilding.setNumberOfRoomsOnFloor(testNumberRoomsOnFloor);
        clonedTestDwellingBuilding.setNameOfBuilding(testWrongNameOfBuilding);
        assertNotEquals(testDwellingBuilding, clonedTestDwellingBuilding);
    }

    @Test
    public void testHashCode() throws Exception {
        DwellingBuilding clonedTestDwellingBuilding = (DwellingBuilding) testDwellingBuilding.clone();
        int expectedHashCode = testDwellingBuilding.hashCode();
        assertEquals(expectedHashCode, clonedTestDwellingBuilding.hashCode());

        clonedTestDwellingBuilding.setNumberOfFloors(testWrongNumberOfFloors);
        assertNotEquals(expectedHashCode, clonedTestDwellingBuilding.hashCode());

        clonedTestDwellingBuilding.setNumberOfFloors(testNumberOfFloors);
        clonedTestDwellingBuilding.setNumberOfRoomsOnFloor(testWrongNumberRoomsOnFloor);
        assertNotEquals(expectedHashCode, clonedTestDwellingBuilding.hashCode());

        clonedTestDwellingBuilding.setNumberOfRoomsOnFloor(testNumberRoomsOnFloor);
        clonedTestDwellingBuilding.setNameOfBuilding(testWrongNameOfBuilding);
        assertNotEquals(expectedHashCode, clonedTestDwellingBuilding.hashCode());
    }
}