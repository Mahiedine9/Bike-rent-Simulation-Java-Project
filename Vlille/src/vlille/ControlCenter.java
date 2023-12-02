package vlille;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import exceptions.*;
import java.util.Timer;
import java.util.TimerTask;

public class ControlCenter {
    private static ControlCenter instance; 
    private ArrayList<Station> stations;
    private List<Service> services; // Services available for repairing bikes
    private ArrayList<Bike> bikesRented; // Bikes that are currently rented
    private ArrayList<Bike> bikesDeposit; // Bikes that are deposited at stations
    private RedistributionStrategy modeDistribution;
    private Timer intervalle;


    private ControlCenter(RedistributionStrategy modeDistribution, int maxRental) {
        this.stations = new ArrayList<>();
        this.stations = new ArrayList<>();
        this.services = new ArrayList<>();
        this.bikesRented = new ArrayList<>();
        this.bikesDeposit = new ArrayList<>();
        this.modeDistribution = modeDistribution;
        this.stations = new ArrayList<>(stations);
    }

    public static ControlCenter getInstance(RedistributionStrategy modeDistribution, int maxRental) {
        if (instance == null) {
            instance = new ControlCenter(modeDistribution, maxRental);
        }
        return instance;
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

    public void distribute() {
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

    public void randomDepositWithdrawal() {
        Random rand = new Random();
        for (Station station : stations) {
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
    

    
    public void depositWithdrawalSimulateIntervalle() {
        intervalle.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                randomDepositWithdrawal();
                System.out.println("random Deposit and Withdrawal done");
            }
        }, 0, 40 *1000);
    }


    public void EmptyStationsSimulateIntervalle(){
        intervalle.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                for (Station station : stations){
                    if (station.getNumberOfBikes() == 0){
                        distribute();
                        System.out.println("distribute done");
                    } 
                } 
                
            }
        }, 0, 80 *1000);


    }

    public List<Station> getStations() {
        return this.stations; // Return a copy of the stations list to avoid external modifications
    }

    public void AddStation(Station station){
        this.stations.add(station) ;
    } 
    
}

