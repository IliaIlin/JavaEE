package com.jee;

import com.jee.impl.DwellingBuilding;
import com.jee.impl.OfficeBuilding;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class BuildingIOTest {

    Building testOfficeBuilding, testDwellingBuilding;

    FileInputStream fileInputStream;
    FileOutputStream fileOutputStream;
    FileReader fileReader;
    Scanner scanner;

    final String TEST_FILE_PATH = "src/test/resources/test.txt";

    @Before
    public void setUp() throws Exception {
        testOfficeBuilding = new OfficeBuilding(17, new int[]{
                2, 3, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 2}, "testOfficeBuilding");
        testDwellingBuilding = new DwellingBuilding(10, new int[]{
                1, 20, 20, 20, 20, 20, 20, 20, 20, 10}, "testDwellingBuilding");
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
        try (FileWriter fileWriter = new FileWriter(TEST_FILE_PATH)) {
            BuildingIO.printFloorDTOListToFile(testOfficeBuilding.getAllFloors(), fileWriter);
        }
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