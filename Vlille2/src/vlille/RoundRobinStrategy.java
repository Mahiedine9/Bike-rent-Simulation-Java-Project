package vlille;

import java.util.List;
import java.util.ArrayList;
import exceptions.*;

public class RoundRobinStrategy implements RedistributionStrategy {

    private ControlCenter controlCenter;

    public RoundRobinStrategy(ControlCenter controlCenter) {
        this.controlCenter = controlCenter;
    }
    
    @Override
    public void redistribute() {
        List<Station> stations = controlCenter.getStations();
        List<Bike> bikesForRedistribution = getBikesForRedistribution(stations);

        for (Station station : stations) {
            int bikesNeeded = station.getCapacity() - station.getNumberOfBikes();

            while (bikesNeeded > 0 && !bikesForRedistribution.isEmpty()) {
                Bike bike = bikesForRedistribution.remove(0);
                try {
                    station.addBike(bike, station.findEmptySlot()); // Assuming Station has a method to find an empty slot
                    bikesNeeded--;
                } catch (OccupiedLocationException e) {
                    // Handle the situation where the slot is unexpectedly occupied
                    e.printStackTrace();
                }
            }
        }
    }

    private List<Bike> getBikesForRedistribution(List<Station> stations) {
        List<Bike> bikes = new ArrayList<>();
        for (Station station : stations) {
            while (station.getNumberOfBikes() > station.getCapacity()) {
                try {
                    Bike bikeToRemove = station.selectBikeForRemoval(); // This method needs to be implemented in Station
                    if (bikeToRemove != null) {
                        station.TakeBike(bikeToRemove);
                        bikes.add(bikeToRemove);
                    }
                } catch (BikeNotRemovableException e) {
                    // Handle the exception, e.g., skip to the next bike or break the loop
                    e.printStackTrace();
                }
            }
        }
        return bikes;
    }
}
