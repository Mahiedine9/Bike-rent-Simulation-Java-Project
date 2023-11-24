package vlille;

import java.util.HashMap;
import java.util.Map;

public class Station {
    int capacity;
    int BikeCount;
    private String stationId;
    private Map<String, Bike> bikes;

    // Constructor
    public Station(String stationId, int capacity, int BikeCount) {
        this.stationId = stationId;
        this.capacity = capacity;
        this.BikeCount = BikeCount;
        this.bikes = new HashMap<>();
    }

    // Add a bike to the station
    public void addBike(Bike bike) {
        bikes.put(bike.getId(), bike);
        System.out.println("Bike " + bike.getId() + " added to station " + stationId);
    }

    // Rent a bike from the station
    public void rentBike(String bikeId) {
        Bike bike = bikes.get(bikeId);
        if (bike != null && bike.isAvailable()) {
            bike.rent();
        } else {
            System.out.println("Bike " + bikeId + " is not available for rent at station " + stationId);
        }
    }

    // Return a bike to the station
    public void returnBike(Bike bike) {
        if (bikes.containsKey(bike.getId())) {
            bike.deposit();
            BikeCount++;
        } else {
            System.out.println("Bike " + bike.getId() + " does not belong to station " + stationId);
        }
    }

    // Getter for station ID
    public String getStationId() {
        return stationId;
    }

    public int getCapacity() {
        return this.capacity;
    }

    // Method to get the current bike count at the station
    public int getBikeCount() {
        return this.BikeCount;
    }

    public Bike removeBike() {
        if (bikes.isEmpty()) {
            // Handle the case where no bikes are available
            return null; // or throw an exception
        }

        String bikeId = bikes.keySet().iterator().next(); // Get the ID of any bike
        return bikes.remove(bikeId); // Remove and return the bike
    }
}
