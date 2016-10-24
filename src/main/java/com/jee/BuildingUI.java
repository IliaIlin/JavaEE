package com.jee;

import com.jee.Building;
import com.jee.BuildingIO;
import com.jee.dto.FloorDTO;
import com.jee.exceptions.GeneralInputException;
import com.jee.exceptions.NegativeInputNumberException;
import com.jee.exceptions.WrongBuildingTypeException;
import com.jee.impl.DwellingBuilding;
import com.jee.impl.OfficeBuilding;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class BuildingUI {

    static Scanner sc = new Scanner(System.in);
    static int numberOfBuildings;
    static Building[] buildings;
    static OfficeBuilding[] officeBuildings;
    static DwellingBuilding[] dwellingBuildings;
    static final String INPUT_FILE_PATH = "src/lab3/inputFiles/";
    static final String OUTPUT_FILE_PATH = "src/lab3/outputFiles/";

    public static void main(String[] args) {
        inputUserChoice();
        eventUserChoice();
    }

    public static void inputUserChoice() {
        while (true) {
            System.out.println("________________________________________________");
            System.out.println("1. Input from console");
            System.out.println("2. Input from byte file");
            System.out.println("3. Input from symbol file");
            System.out.println("________________________________________________");
            try {
                int userChoice = sc.nextInt();
                switch (userChoice) {
                    case 1:
                        inputDataFromConsole();
                        return;
                    case 2:
                        System.out.println("Specify name of the file. Working directory is: " + INPUT_FILE_PATH);
                        inputDataFromByteStream(sc.next());
                        return;
                    case 3:
                        System.out.println("Specify name of the file. Working directory is: " + INPUT_FILE_PATH);
                        inputDataFromSymbolStream(sc.next());
                        return;
                    default:
                        throw new GeneralInputException("User choice is incorrect");
                }
            } catch (GeneralInputException | InputMismatchException exception) {
                System.out.println(exception.getMessage() != null ? exception.getMessage() : "Number is expected");
                sc = new Scanner(System.in);
            }
        }
    }

    public static void eventUserChoice() {
        while (true) {
            System.out.println("________________________________________________");
            System.out.println("1. Print all data to the console");
            System.out.println("2. Print all data to the byte file");
            System.out.println("3. Print all data to the symbol file");
            System.out.println("4. Find all buildings with the same rooms total");
            System.out.println("5. Separate data by building types");
            System.out.println("6. Find floor with max rooms for each building and print them to file");
            System.out.println("7. Find floor with min rooms for each building and print them to file");
            System.out.println("8. Serialize building first building in array");
            System.out.println("9. Deserialize building from file");
            System.out.println("10. Exit");
            System.out.println("________________________________________________");
            try {
                int userChoice = sc.nextInt();
                if (userChoice == 10) return;
                switch (userChoice) {
                    case 1:
                        printDataToConsole(buildings);
                        break;
                    case 2:
                        System.out.println("Specify name of the file. Working directory is: " + OUTPUT_FILE_PATH);
                        printDataToByteStream(buildings, sc.next());
                        break;
                    case 3:
                        System.out.println("Specify name of the file. Working directory is: " + OUTPUT_FILE_PATH);
                        printDataToSymbolStream(buildings, sc.next());
                        break;
                    case 4:
                        printDataToConsole(findBuildingsSameByRoomsTotal());
                        break;
                    case 5:
                        separateBuildingsByType();
                        System.out.println("Office Buildings:");
                        printDataToConsole(officeBuildings);
                        System.out.println("Dwelling Buildings:");
                        printDataToConsole(dwellingBuildings);
                        break;
                    case 6:
                        System.out.println("Specify name of the file. Working directory is: " + OUTPUT_FILE_PATH);
                        printDtoToFile(findFloorsWithMaxRooms(), sc.next());
                        break;
                    case 7:
                        System.out.println("Specify name of the file. Working directory is: " + OUTPUT_FILE_PATH);
                        printDtoToFile(findFloorsWithMinRooms(), sc.next());
                        break;
                    case 8:
                        System.out.println("Specify name of the file. Working directory is: " + OUTPUT_FILE_PATH);
                        BuildingIO.serializeBuilding(buildings[0], OUTPUT_FILE_PATH + sc.next());
                        break;
                    case 9:
                        System.out.println("Specify name of the file. Working directory is: " + OUTPUT_FILE_PATH);
                        Building deserializedBuilding = BuildingIO.deserializeBuilding(OUTPUT_FILE_PATH + sc.next());
                        System.out.println(deserializedBuilding.toString());
                        break;
                    default:
                        throw new GeneralInputException("User choice is incorrect");
                }
            } catch (GeneralInputException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    public static void inputDataFromConsole() throws GeneralInputException {
        System.out.println("Enter number of buildings: ");
        try {
            int numberOfBuildings = sc.nextInt();
            if (numberOfBuildings < 0) throw new NegativeInputNumberException(
                    "Number of buildings should be positive");
            buildings = new Building[numberOfBuildings];
            for (int i = 0; i < numberOfBuildings; i++) {
                buildings[i] = BuildingIO.readBuilding(sc);
            }
        } catch (InputMismatchException | NegativeInputNumberException | WrongBuildingTypeException exception) {
            throw new GeneralInputException(exception.getMessage());
        }
    }

    public static void inputDataFromSymbolStream(String fileName) throws GeneralInputException {
        try (FileReader fileReader = new FileReader(INPUT_FILE_PATH + fileName)) {
            StreamTokenizer streamTokenizer = new StreamTokenizer(fileReader);
            streamTokenizer.nextToken();
            numberOfBuildings = (int) streamTokenizer.nval;
            buildings = new Building[numberOfBuildings];
            for (int i = 0; i < numberOfBuildings; i++) {
                buildings[i] = BuildingIO.readBuilding(fileReader);
            }
        } catch (FileNotFoundException exception) {
            throw new GeneralInputException("File is not found.");
        } catch (IOException exception) {
            throw new GeneralInputException("Error occured while reading from file. Check file's content and try again");
        }
    }

    public static void inputDataFromByteStream(String fileName) throws GeneralInputException {
        try (FileInputStream fileInputStream = new FileInputStream(INPUT_FILE_PATH + fileName)) {
            numberOfBuildings = fileInputStream.read();
            buildings = new Building[numberOfBuildings];
            for (int i = 0; i < numberOfBuildings; i++) {
                buildings[i] = BuildingIO.inputBuilding(fileInputStream);
            }
        } catch (FileNotFoundException exception) {
            throw new GeneralInputException("File is not found.");
        } catch (IOException exception) {
            throw new GeneralInputException("Error occured while reading from file. Check file's content and try again");
        }
    }

    public static void printDataToConsole(Building[] buildings) {
        Arrays.stream(buildings).forEach((building) -> System.out.println(building.toString()));
    }

    public static void printDataToSymbolStream(Building[] buildings, String fileName) throws GeneralInputException {
        try (FileWriter fileWriter = new FileWriter(OUTPUT_FILE_PATH + fileName)) {
            fileWriter.write(buildings.length + "\n");
            Arrays.stream(buildings).forEach((building) -> BuildingIO.writeBuilding(building, fileWriter));
        } catch (IOException exception) {
            throw new GeneralInputException("Error occured while printing data to the symbol stream. Try again");
        }
    }

    public static void printDataToByteStream(Building[] buildings, String fileName) throws GeneralInputException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(OUTPUT_FILE_PATH + fileName)) {
            fileOutputStream.write(buildings.length);
            for (Building building : buildings) {
                BuildingIO.outputBuilding(building, fileOutputStream);
            }
        } catch (IOException exception) {
            throw new GeneralInputException("Error occured while printing data to the byte stream. Try again");
        }
    }

    public static void printDtoToFile(List<FloorDTO> floors, String fileName) throws GeneralInputException {
        try (FileWriter fileWriter = new FileWriter(OUTPUT_FILE_PATH + fileName)) {
            BuildingIO.printFloorDTOListToFile(floors, fileWriter);
        } catch (IOException exception) {
            throw new GeneralInputException("Error occured while printing floors to the file. Try again");
        }
    }

    public static Building[] findBuildingsSameByRoomsTotal() {
        List<Integer> totalRoomsInBuildings = Arrays.stream(buildings).
                map(building -> building.countTotalOfRooms()).
                collect(Collectors.toList());
        List<Building> buildingsWithSameTotal = new ArrayList<>();
        for (int i = 0; i < buildings.length; i++) {
            if (Collections.frequency(totalRoomsInBuildings, totalRoomsInBuildings.get(i)) > 1) {
                buildingsWithSameTotal.add(buildings[i]);
            }
        }
        return buildingsWithSameTotal.toArray(new Building[0]);
    }

    public static void separateBuildingsByType() {
        officeBuildings = Arrays.stream(buildings).
                filter(building -> building instanceof OfficeBuilding).
                collect(Collectors.toList()).toArray(new OfficeBuilding[0]);
        dwellingBuildings = Arrays.stream(buildings).
                filter(building -> building instanceof DwellingBuilding).
                collect(Collectors.toList()).toArray(new DwellingBuilding[0]);
    }

    public static List<FloorDTO> findFloorsWithMaxRooms() {
        return Arrays.stream(buildings).
                map(building -> building.findFloorWithMaxRooms()).
                collect(Collectors.toList());
    }

    public static List<FloorDTO> findFloorsWithMinRooms() {
        return Arrays.stream(buildings).
                map(building -> building.findFloorWithMinRooms()).
                collect(Collectors.toList());
    }


}


