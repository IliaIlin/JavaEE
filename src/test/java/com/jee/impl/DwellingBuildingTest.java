package com.jee.impl;

import com.jee.dto.FloorDTO;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class DwellingBuildingTest {

    private static int testNumberOfFloors;
    private static int testWrongNumberOfFloors;
    private static int[] testNumberRoomsOnFloor;
    private static int[] testWrongNumberRoomsOnFloor;
    private static int[] testLessRoomsOnFloor;
    private static int[] testMoreRoomsOnFloor;
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
        testWrongNumberOfFloors = 4;
        testNumberRoomsOnFloor = new int[]{1, 2, 3};
        testWrongNumberRoomsOnFloor = new int[]{3, 2, 1};
        testLessRoomsOnFloor = new int[]{1, 1, 1};
        testMoreRoomsOnFloor = new int[]{2, 3, 4, 5};
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

    @Test
    public void testIterator() {
        Iterator iterator = testDwellingBuilding.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            assertEquals(testNumberRoomsOnFloor[i], iterator.next());
            i++;
        }
    }

    @Test(expected = NoSuchElementException.class)
    public void testOutOfBoundsIterator() {
        Iterator iterator = testDwellingBuilding.iterator();
        for (int i = 0; i < testWrongNumberOfFloors; i++)
            iterator.next();
    }

    @Test
    public void testCompareTo() {
        DwellingBuilding clonedDwellingBuilding = (DwellingBuilding) testDwellingBuilding.clone();
        assertTrue(testDwellingBuilding.compareTo(clonedDwellingBuilding) == 0);

        clonedDwellingBuilding.setNumberOfRoomsOnFloor(testLessRoomsOnFloor);
        assertTrue(testDwellingBuilding.compareTo(clonedDwellingBuilding) > 0);

        clonedDwellingBuilding.setNumberOfRoomsOnFloor(testMoreRoomsOnFloor);
        assertTrue(testDwellingBuilding.compareTo(clonedDwellingBuilding) < 0);
    }
}