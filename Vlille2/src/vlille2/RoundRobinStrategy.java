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

        while (!bikesForRedistribution.isEmpty()) {
            for (Station station : stations) {
                if (station.getNumberOfBikes() < station.getCapacity() && !bikesForRedistribution.isEmpty()) {
                    Bike bike = bikesForRedistribution.remove(0);
                    try {
                        station.addBike(bike, station.findEmptySlot());
                    } catch (OccupiedLocationException e) {
                        System.err.println("Failed to add bike: " + e.getMessage());
                        // If bike couldn't be added due to an unexpected error, put it back in the list
                        bikesForRedistribution.add(bike);
                    }
                }
            }
        }
    }

    private List<Bike> getBikesForRedistribution(List<Station> stations) {
        List<Bike> bikes = new ArrayList<>();
        for (Station station : stations) {
            while (station.getNumberOfBikes() > station.getCapacity()) {
                try {
                    Bike bikeToRemove = station.selectBikeForRemoval();
                    if (bikeToRemove != null) {
                        station.TakeBike(bikeToRemove);
                        bikes.add(bikeToRemove);
                    }
                } catch (BikeNotRemovableException e) {
                    System.err.println("Failed to remove bike: " + e.getMessage());
                    // If bike couldn't be removed due to an unexpected error, break the loop
                    break;
                }
            }
        }
        return bikes;
    }


    public void setControlCenter(ControlCenter controlCenter) {
        this.controlCenter = controlCenter;
    }
    
}
