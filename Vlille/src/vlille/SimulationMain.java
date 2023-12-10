package vlille;

import java.util.*;
import java.util.Random;
import java.util.Scanner;
import exceptions.*;

public class SimulationMain {
    // Method for random simulation
    public static void runRandomSimulation() {
        // Initialize Control Center and strategies
        RedistributionStrategy strategy = new RandomStrategy(ControlCenter.getInstance(null, 10));
        ControlCenter controlCenter = ControlCenter.getInstance(strategy, 10);

        // Create Stations
        ArrayList<Station> stations = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            stations.add(new Station("Station " + i, new Random().nextInt(11) + 10)); // Stations with capacity between 10 and 20
        }

        // Add Stations to Control Center
        for (Station station : stations) {
            controlCenter.AddStation(station);
        }

        // Create Bikes and add to Stations
        for (int i = 0; i < 50; i++) {
            Bike bike = i % 2 == 0 ? new ClassicBike(i, new Random().nextBoolean(), new Random().nextBoolean(), new InService())
                                    : new ElectricBike(i, new Random().nextBoolean(), new Random().nextBoolean(), new InService());
            Station station = stations.get(new Random().nextInt(stations.size()));
            try {
                station.addBike(bike, new Random().nextInt(station.getCapacity()));
            } catch (OccupiedLocationException e) {
                System.err.println(e.getMessage());
            }
        }

        // Simulation Loop
        for (int i = 0; i < 100; i++) {
            System.out.println("Simulation iteration: " + (i + 1));
            
            // Simulate random bike deposits and withdrawals
            controlCenter.randomDepositWithdrawal();

            // Pause for a short time to simulate time passing
            try {
                Thread.sleep(1000); // Pause for 1 second
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Simulation interrupted");
                break;
            }
        }

        // Final status report
        for (Station station : stations) {
            System.out.println(station.getName() + " has " + station.getNumberOfBikes() + " bikes");
        }
    }


    public static void main(String[] args) {
        // Interactive simulation logic
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select Mode: ");
        System.out.println("1. Random Simulation");
        System.out.println("2. Interactive Simulation");
        int choice = scanner.nextInt();

        if (choice == 1) {
            runRandomSimulation();
        } else {
            // Initialize the control center and strategy
            RandomStrategy randomStrategy = new RandomStrategy(null);
            ControlCenter controlCenter = ControlCenter.getInstance(randomStrategy, 10);
            randomStrategy.SetControlCenter(controlCenter);
            Random random = new Random();

            // Create stations
            System.out.print("How many stations would you like to create? ");
            int nbOfStations = scanner.nextInt();
            for (int i = 0; i < nbOfStations; i++) {
                Station station = new Station("Station " + i, random.nextInt(10) + 10);
                for (int j = 0; j < station.getCapacity(); j++) {
                    Bike bike = random.nextBoolean() ? new ClassicBike(j, random.nextBoolean(), random.nextBoolean(), new InService())
                                                     : new ElectricBike(j, random.nextBoolean(), random.nextBoolean(), new InService());
                    try {
                        station.addBike(bike, j);
                    } catch (OccupiedLocationException e) {
                        System.err.println("Location at " + j + " in " + station.getName() + " is already occupied. " + e.getMessage());
                    }
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
                int actionChoice = scanner.nextInt();


                switch (actionChoice) {
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
                    System.out.println("Bikes redistributed. Current state of stations:");
                
                    for (Station station : controlCenter.getStations()) {
                        System.out.println(station.getName() + " has " + station.getNumberOfBikes() + " bikes.");
                        for (Bike bike : station.getBikes()) {
                            if (bike != null) {
                                System.out.println("    Bike ID: " + bike.GetId() + ", State: " + bike.GetState().getClass().getSimpleName());
                            }
                        }
                    }
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
}
