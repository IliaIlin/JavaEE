package com.jee;

import com.jee.dto.FloorDTO;
import com.jee.exceptions.GeneralInputException;
import com.jee.exceptions.NegativeInputNumberException;
import com.jee.impl.DwellingBuilding;
import com.jee.impl.OfficeBuilding;
import com.jee.util.BuildingUtils;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class BuildingUI {

    static final String INPUT_FILE_PATH = "src/main/resources/inputFiles/";
    static final String OUTPUT_FILE_PATH = "src/main/resources/outputFiles/";
    static Scanner sc = new Scanner(System.in);
    static List<Building> buildings = new ArrayList<>();
    static List<Building> officeBuildings = new ArrayList<>();
    static List<Building> dwellingBuildings = new ArrayList<>();

    public static void main(String[] args)
    {
        inputUserChoice();
        eventUserChoice();
    }

    public static void inputUserChoice()
    {
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

    public static void eventUserChoice()
    {
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
                        BuildingUtils.serializeBuilding(buildings.get(0), OUTPUT_FILE_PATH + sc.next());
                        break;
                    case 9:
                        System.out.println("Specify name of the file. Working directory is: " + OUTPUT_FILE_PATH);
                        Building deserializedBuilding = BuildingUtils.deserializeBuilding(OUTPUT_FILE_PATH + sc.next());
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

    public static void inputDataFromConsole() throws GeneralInputException
    {
        buildings = new ArrayList<>();
        System.out.println("Enter number of buildings: ");
        try {
            int numberOfBuildings = sc.nextInt();
            if (numberOfBuildings < 0) throw new NegativeInputNumberException(
                    "Number of buildings should be positive");
            for (int i = 0; i < numberOfBuildings; i++) {
                buildings.add(BuildingUtils.readBuilding(sc));
            }
        } catch (InputMismatchException | NegativeInputNumberException exception) {
            throw new GeneralInputException(exception.getMessage());
        }
    }

    public static void inputDataFromSymbolStream(String fileName) throws GeneralInputException
    {
        try (FileReader fileReader = new FileReader(INPUT_FILE_PATH + fileName)) {
            StreamTokenizer streamTokenizer = new StreamTokenizer(fileReader);
            streamTokenizer.nextToken();
            int numberOfBuildings = (int) streamTokenizer.nval;
            for (int i = 0; i < numberOfBuildings; i++) {
                buildings.add(BuildingUtils.readBuilding(fileReader));
            }
        } catch (FileNotFoundException exception) {
            throw new GeneralInputException("File is not found.");
        } catch (IOException exception) {
            throw new GeneralInputException("Error occured while reading from file. Check file's content and try again");
        }
    }

    public static void inputDataFromByteStream(String fileName) throws GeneralInputException
    {
        try (FileInputStream fileInputStream = new FileInputStream(INPUT_FILE_PATH + fileName)) {
            int numberOfBuildings = fileInputStream.read();
            for (int i = 0; i < numberOfBuildings; i++) {
                buildings.add(BuildingUtils.inputBuilding(fileInputStream));
            }
        } catch (FileNotFoundException exception) {
            throw new GeneralInputException("File is not found.");
        } catch (IOException exception) {
            throw new GeneralInputException("Error occured while reading from file. Check file's content and try again");
        }
    }

    public static void printDataToConsole(List<Building> buildings)
    {
        buildings.forEach(System.out::println);
    }

    public static void printDataToSymbolStream(List<Building> buildings, String fileName) throws GeneralInputException
    {
        try (FileWriter fileWriter = new FileWriter(OUTPUT_FILE_PATH + fileName)) {
            fileWriter.write(buildings.size() + "\n");
            buildings.forEach((building) -> BuildingUtils.writeBuilding(building, fileWriter));
        } catch (IOException exception) {
            throw new GeneralInputException("Error occured while printing data to the symbol stream. Try again");
        }
    }

    public static void printDataToByteStream(List<Building> buildings, String fileName) throws GeneralInputException
    {
        try (FileOutputStream fileOutputStream = new FileOutputStream(OUTPUT_FILE_PATH + fileName)) {
            fileOutputStream.write(buildings.size());
            for (Building building : buildings) {
                BuildingUtils.outputBuilding(building, fileOutputStream);
            }
        } catch (IOException exception) {
            throw new GeneralInputException("Error occured while printing data to the byte stream. Try again");
        }
    }

    public static void printDtoToFile(List<FloorDTO> floors, String fileName) throws GeneralInputException
    {
        try (FileWriter fileWriter = new FileWriter(OUTPUT_FILE_PATH + fileName)) {
            BuildingUtils.printFloorDTOListToFile(floors, fileWriter);
        } catch (IOException exception) {
            throw new GeneralInputException("Error occured while printing floors to the file. Try again");
        }
    }

    public static List<Building> findBuildingsSameByRoomsTotal()
    {
        List<Integer> totalRoomsInBuildings = buildings.stream()
                .map(building -> building.countTotalOfRooms())
                .collect(Collectors.toList());
        List<Building> buildingsWithSameTotal = new ArrayList<>();
        for (int i = 0; i < buildings.size(); i++) {
            if (Collections.frequency(totalRoomsInBuildings, totalRoomsInBuildings.get(i)) > 1) {
                buildingsWithSameTotal.add(buildings.get(i));
            }
        }
        return buildingsWithSameTotal;
    }

    public static void separateBuildingsByType()
    {
        officeBuildings = buildings.stream()
                .filter(building -> building instanceof OfficeBuilding)
                .collect(Collectors.toList());

        dwellingBuildings = buildings.stream()
                .filter(building -> building instanceof DwellingBuilding)
                .collect(Collectors.toList());
    }

    public static List<FloorDTO> findFloorsWithMaxRooms()
    {
        return buildings.stream()
                .map(building -> building.findFloorWithMaxRooms())
                .collect(Collectors.toList());
    }

    public static List<FloorDTO> findFloorsWithMinRooms()
    {
        return buildings.stream()
                .map(building -> building.findFloorWithMinRooms())
                .collect(Collectors.toList());
    }


}


