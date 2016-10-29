package com.jee;

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

public class BuildingIOTest {

    private static Building testOfficeBuilding, testDwellingBuilding;

    private FileInputStream fileInputStream;
    private FileOutputStream fileOutputStream;
    private FileReader fileReader;
    private Scanner scanner;

    private final String TEST_FILE_PATH = "src/test/resources/test.txt";

    @BeforeClass
    public static void setUp() throws Exception {
        testOfficeBuilding = new OfficeBuilding(17, new int[]{
                2, 3, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 2}, "testOfficeBuilding");
        testDwellingBuilding = new DwellingBuilding(10, new int[]{
                1, 20, 20, 20, 20, 20, 20, 20, 20, 10}, "testDwellingBuilding");
    }

    @Before
    public void setUpStreams() throws FileNotFoundException {
        fileInputStream = new FileInputStream(TEST_FILE_PATH);
        fileOutputStream = new FileOutputStream(TEST_FILE_PATH);
        fileReader = new FileReader(TEST_FILE_PATH);
        scanner = new Scanner(fileInputStream);
    }

    @Test
    public void testInputOutputOfficeBuilding() throws Exception {
        BuildingIO.outputBuilding(testOfficeBuilding, fileOutputStream);
        Building building = BuildingIO.inputBuilding(fileInputStream);
        assertEquals(testOfficeBuilding, building);
    }

    @Test
    public void testInputOutputDwellingBuilding() throws Exception {
        BuildingIO.outputBuilding(testDwellingBuilding, fileOutputStream);
        Building building = BuildingIO.inputBuilding(fileInputStream);
        assertEquals(testDwellingBuilding, building);
    }

    @Test
    public void testReadWriteOfficeBuilding() throws Exception {
        try (FileWriter fileWriter = new FileWriter(TEST_FILE_PATH)) {
            BuildingIO.writeBuilding(testOfficeBuilding, fileWriter);
        }
        Building building = BuildingIO.readBuilding(fileReader);
        assertEquals(testOfficeBuilding, building);
    }

    @Test
    public void testReadWriteDwellingBuilding() throws Exception {
        try (FileWriter fileWriter = new FileWriter(TEST_FILE_PATH)) {
            BuildingIO.writeBuilding(testDwellingBuilding, fileWriter);
        }
        Building building = BuildingIO.readBuilding(fileReader);
        assertEquals(testDwellingBuilding, building);
    }

    @Test
    public void testOverloadedReadOfficeBuilding() throws Exception {
        try (FileWriter fileWriter = new FileWriter(TEST_FILE_PATH)) {
            BuildingIO.writeBuilding(testOfficeBuilding, fileWriter);
        }
        Building building = BuildingIO.readBuilding(scanner);
        assertEquals(testOfficeBuilding, building);
    }

    @Test
    public void testOverloadedReadDwellingBuilding() throws Exception {
        try (FileWriter fileWriter = new FileWriter(TEST_FILE_PATH)) {
            BuildingIO.writeBuilding(testDwellingBuilding, fileWriter);
        }
        Building building = BuildingIO.readBuilding(scanner);
        assertEquals(testDwellingBuilding, building);
    }

    @Test
    @Ignore
    public void testPrintFloorDTOListToFile() throws Exception {
        List<FloorDTO> listToWrite = testOfficeBuilding.getAllFloors();
        try (FileWriter fileWriter = new FileWriter(TEST_FILE_PATH)) {
            BuildingIO.printFloorDTOListToFile(listToWrite, fileWriter);
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
        BuildingIO.serializeBuilding(testOfficeBuilding, TEST_FILE_PATH);
        Building building = BuildingIO.deserializeBuilding(TEST_FILE_PATH);
        assertEquals(testOfficeBuilding, building);
    }

    @Test
    public void testSerializeDeserializeDwellingBuilding() throws Exception {
        BuildingIO.serializeBuilding(testDwellingBuilding, TEST_FILE_PATH);
        Building building = BuildingIO.deserializeBuilding(TEST_FILE_PATH);
        assertEquals(testDwellingBuilding, building);
    }
}