package com.jee.impl;

import com.jee.dto.FloorDTO;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class OfficeBuildingTest {

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

    private static OfficeBuilding testOfficeBuilding;


    @BeforeClass
    public static void setUp() throws Exception {
        testNumberOfFloors = 3;
        testWrongNumberOfFloors = 4;
        testNumberRoomsOnFloor = new int[]{1, 2, 3};
        testWrongNumberRoomsOnFloor = new int[]{3, 2, 1};
        testLessRoomsOnFloor = new int[]{1, 1, 1};
        testMoreRoomsOnFloor = new int[]{2, 3, 4, 5};
        testNameOfBuilding = "testOfficeBuilding";
        testWrongNameOfBuilding = "testWrongOfficeBuilding";

        testTotalRoomsCount = Arrays.stream(testNumberRoomsOnFloor).sum();
        testFloorWithMaxRooms = new FloorDTO(3, testNameOfBuilding);
        testFloorWithMinRooms = new FloorDTO(1, testNameOfBuilding);

        testAllFloors = new ArrayList<>();
        testAllFloors.add(testFloorWithMinRooms);
        testAllFloors.add(new FloorDTO(2, testNameOfBuilding));
        testAllFloors.add(testFloorWithMaxRooms);

        testOfficeBuilding = new OfficeBuilding(testNumberOfFloors,
                testNumberRoomsOnFloor, testNameOfBuilding);
    }

    @Test
    public void testCountTotalOfRooms() throws Exception {
        assertEquals(testTotalRoomsCount, testOfficeBuilding.countTotalOfRooms());
    }

    @Test
    public void testFindFloorWithMaxRooms() throws Exception {
        assertEquals(testFloorWithMaxRooms, testOfficeBuilding.findFloorWithMaxRooms());
    }

    @Test
    public void testFindFloorWithMinRooms() throws Exception {
        assertEquals(testFloorWithMinRooms, testOfficeBuilding.findFloorWithMinRooms());
    }

    @Test
    public void testGetAllFloors() throws Exception {
        assertEquals(testAllFloors, testOfficeBuilding.getAllFloors());
    }

    @Test
    public void testClone() throws Exception {
        assertEquals(testOfficeBuilding, testOfficeBuilding.clone());
    }

    @Test
    public void testEquals() throws Exception {
        OfficeBuilding clonedTestOfficeBuilding = (OfficeBuilding) testOfficeBuilding.clone();
        assertEquals(testOfficeBuilding, clonedTestOfficeBuilding);

        clonedTestOfficeBuilding.setNumberOfFloors(testWrongNumberOfFloors);
        assertNotEquals(testOfficeBuilding, clonedTestOfficeBuilding);

        clonedTestOfficeBuilding.setNumberOfFloors(testNumberOfFloors);
        clonedTestOfficeBuilding.setNumberOfRoomsOnFloor(testWrongNumberRoomsOnFloor);
        assertNotEquals(testOfficeBuilding, clonedTestOfficeBuilding);

        clonedTestOfficeBuilding.setNumberOfRoomsOnFloor(testNumberRoomsOnFloor);
        clonedTestOfficeBuilding.setNameOfBuilding(testWrongNameOfBuilding);
        assertNotEquals(testOfficeBuilding, clonedTestOfficeBuilding);
    }

    @Test
    public void testHashCode() throws Exception {
        OfficeBuilding clonedTestOfficeBuilding = (OfficeBuilding) testOfficeBuilding.clone();
        int expectedHashCode = testOfficeBuilding.hashCode();
        assertEquals(expectedHashCode, clonedTestOfficeBuilding.hashCode());

        clonedTestOfficeBuilding.setNumberOfFloors(testWrongNumberOfFloors);
        assertNotEquals(expectedHashCode, clonedTestOfficeBuilding.hashCode());

        clonedTestOfficeBuilding.setNumberOfFloors(testNumberOfFloors);
        clonedTestOfficeBuilding.setNumberOfRoomsOnFloor(testWrongNumberRoomsOnFloor);
        assertNotEquals(expectedHashCode, clonedTestOfficeBuilding.hashCode());

        clonedTestOfficeBuilding.setNumberOfRoomsOnFloor(testNumberRoomsOnFloor);
        clonedTestOfficeBuilding.setNameOfBuilding(testWrongNameOfBuilding);
        assertNotEquals(expectedHashCode, clonedTestOfficeBuilding.hashCode());
    }

    @Test
    public void testIterator() {
        Iterator iterator = testOfficeBuilding.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            assertEquals(testNumberRoomsOnFloor[i], iterator.next());
            i++;
        }
    }

    @Test(expected = NoSuchElementException.class)
    public void testOutOfBoundsIterator() {
        Iterator iterator = testOfficeBuilding.iterator();
        for (int i = 0; i < testWrongNumberOfFloors; i++)
            iterator.next();
    }

    @Test
    public void testCompareTo() {
        OfficeBuilding clonedOfficeBuilding = (OfficeBuilding) testOfficeBuilding.clone();
        assertTrue(testOfficeBuilding.compareTo(clonedOfficeBuilding) == 0);

        clonedOfficeBuilding.setNumberOfRoomsOnFloor(testLessRoomsOnFloor);
        assertTrue(testOfficeBuilding.compareTo(clonedOfficeBuilding) > 0);

        clonedOfficeBuilding.setNumberOfRoomsOnFloor(testMoreRoomsOnFloor);
        assertTrue(testOfficeBuilding.compareTo(clonedOfficeBuilding) < 0);
    }
}