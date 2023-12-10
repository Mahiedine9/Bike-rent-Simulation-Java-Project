package vlille;

import java.util.List;
import java.util.Random;
import java.util.Scanner;
import exceptions.*;

public class SimulationMain {

    public static void main(String[] args) throws OccupiedLocationException {
        
        // Initialize the control center and strategy
        RandomStrategy randomStrategy = new RandomStrategy(null);
        ControlCenter controlCenter = ControlCenter.getInstance(randomStrategy, 10);
        randomStrategy.SetControlCenter(controlCenter);
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Create stations
        System.out.print("How many stations would you like to create? ");
        int nbOfStations = scanner.nextInt();
        for (int i = 0; i < nbOfStations; i++) {
            Station station = new Station("Station " + i, random.nextInt(10) + 10); // Stations with random capacity
            for (int j = 0; j < station.getCapacity(); j++) {
                Bike bike = random.nextBoolean() ? new ClassicBike(j, random.nextBoolean(), random.nextBoolean(), new InService())
                                                 : new ElectricBike(j, random.nextBoolean(), random.nextBoolean(), new InService());
                station.addBike(bike, j);
            }
            controlCenter.AddStation(station);
        }

        // Simulation loop
        while (true) {
            System.out.println("\nSimulation Actions:");
            System.out.println("1. Rent a bike");
            System.out.println("2. Return a bike");
            System.out.println("3. Repair bikes");
            System.out.println("4. Redistribute bikes");
            System.out.println("5. Exit simulation");
            System.out.print("Choose an action: ");
            int choice = scanner.nextInt();


            switch (choice) {
                // Case 1: Renting a bike
                case 1:
                System.out.println("Select a station to rent a bike (0-" + (nbOfStations-1) + "):");
                int stationIndexToRent = scanner.nextInt(); 
                Station stationToRent = controlCenter.getStations().get(stationIndexToRent);
                System.out.println("Available bikes at " + stationToRent.getName() + ":");

                for (Bike bike : stationToRent.getBikes()) {
                    if (bike != null && bike.GetState() instanceof InService) { 
                        System.out.println("Bike ID: " + bike.GetId());
                    }
                }
                System.out.println("Enter Bike ID to rent:");
                int bikeIdToRent = scanner.nextInt();
                Bike bikeToRent = stationToRent.getBikes().stream()
                                    .filter(b -> b.GetId() == bikeIdToRent && b.GetState() instanceof InService)
                                    .findFirst()
                                    .orElse(null);
                if (bikeToRent != null) {
                    bikeToRent.SetState(new Rented()); 
                    System.out.println("You have rented Bike ID: " + bikeIdToRent);
                } else {
                    System.out.println("Bike not available for rent.");
                }
                break;

                // Case 2: Returning a bike
                case 2:
                System.out.println("Select a station to return a bike (1-" + nbOfStations + "):");
                int stationIndexToReturn = scanner.nextInt() - 1;
                Station stationToReturn = controlCenter.getStations().get(stationIndexToReturn);
                System.out.println("Enter Bike ID to return:");
                int bikeIdToReturn = scanner.nextInt();

                Bike bikeToReturn = stationToReturn.getBikes().stream()
                                    .filter(b -> b.GetId() == bikeIdToReturn)
                                    .findFirst()
                                    .orElse(null);
                if (bikeToReturn != null && bikeToReturn.GetState().isRented()) {
                    bikeToReturn.SetState(new InService()); 
                    System.out.println("You have returned Bike ID: " + bikeIdToReturn);
                } else {
                    System.out.println("Bike not found or not rented.");
                }
                break;

                case 3:
                    controlCenter.fix();
                    System.out.println("Bikes repaired.");
                    break;
                case 4:
                    controlCenter.distribute();
                    System.out.println("Bikes redistributed.");
                    break;
                case 5:
                    System.out.println("Exiting simulation.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    } 
}
