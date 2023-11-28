package vlille;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import exceptions.*;

public class ControlCenter {
    private ArrayList<Station> stations;
    private int interval; // Interval between checks for redistribution and servicing
    private int maxRental; // Max rentals before a bike needs service
    private List<Service> services; // Services available for repairing bikes
    private ArrayList<Bike> bikesRented; // Bikes that are currently rented
    private ArrayList<Bike> bikesDeposit; // Bikes that are deposited at stations
    private int nbInterval; // Number of intervals for simulation
    private RedistributionStrategy modeDistribution;

    public ControlCenter(int interval, RedistributionStrategy modeDistribution, int maxRental, int nbInterval) {
        this.stations = new ArrayList<>();
        this.services = new ArrayList<>();
        this.bikesRented = new ArrayList<>();
        this.bikesDeposit = new ArrayList<>();
        this.interval = interval;
        this.modeDistribution = modeDistribution;
        this.maxRental = maxRental;
        this.nbInterval = nbInterval;
    }

    // Fixes the bikes that require servicing
    public void fix() {
        for (Service service : services) {
            // Go through each bike in the service queue and repair it
            for (Bike bike : bikesDeposit) {
                if (bike.GetState() instanceof OutOfService || bike.GetState() instanceof UnderReparation) {
                    service.ControlService(bike);
                }
            }
        }
    }

    // Distribute bikes based on the distribution mode
    public void distribute() {
        // Implementation of bike distribution logic based on modeDistribution
        // For simplicity, assuming modeDistribution is a functional interface
        modeDistribution.redistribute();
    }

    public void addService(Service service) {
        this.services.add(service);
    }

    public void removeService(Service service) {
        this.services.remove(service);
    }

    public void addBikeToRent(Bike bike) {
        this.bikesRented.add(bike);
    }

    public void addBikeToDeposit(Bike bike) {
        this.bikesDeposit.add(bike);
    }

    // Simulates actions that occur within each interval
    public void simulateInterval() {
        // Go through each station and simulate activity
        for (Station station : stations) {
            // Simulate bike rentals
            for (Bike bike : station.getBikes()) {
                if (bike.GetState() instanceof InService) {
                    bike.UpdateRentalCount();
                    if (bike.GetRentalCount() >= maxRental) {
                        bike.SetState(new OutOfService());
                        bikesDeposit.add(bike);
                    }
                }
            }
        }
    }

    // Simulates random deposits and withdrawals of bikes at stations
    public void randomDepositWithdrawal() {
        Random rand = new Random();
        for (Station station : stations) {
            // Randomly select a bike to deposit or withdraw
            if (rand.nextBoolean() && !bikesRented.isEmpty()) {
                // Randomly remove a bike from the rented list and deposit it
                Bike bike = bikesRented.remove(rand.nextInt(bikesRented.size()));
                try {
                    station.addBike(bike, rand.nextInt(station.getCapacity())); // This might throw OccupiedLocationException
                } catch (OccupiedLocationException e) {
                    // Handle the exception, e.g., retry with a different slot or log the error
                    System.err.println("Failed to deposit bike: " + e.getMessage());
                    // Optionally, you can add the bike back to the bikesRented list if the deposit fails
                    bikesRented.add(bike);
                }
            } else if (!station.getBikes().isEmpty()) {
                // Randomly withdraw a bike from the station
                Bike bike = station.getBikes().remove(rand.nextInt(station.getBikes().size()));
                bikesRented.add(bike);
            }
        }
    }
    

    // Runs the overall simulation
    public void simulation() {
        for (int i = 0; i < nbInterval; i++) {
            simulateInterval();
            fix();
            randomDepositWithdrawal();
            distribute();
            // Additional simulation steps as needed...
        }
    }

    public List<Station> getStations() {
        return new ArrayList<>(stations); // Return a copy of the stations list to avoid external modifications
    }
    
}

