package vlille;

import java.util.List;
import java.util.Random;
import exceptions.*;

public class RandomStrategy implements RedistributionStrategy {

    private ControlCenter controlCenter;
    private Random random = new Random();
    private final double IDEAL_CAPACITY_PERCENTAGE = 0.75; // 75% of total capacity as ideal

    public RandomStrategy(ControlCenter controlCenter) {
        this.controlCenter = controlCenter;
    }

    
    @Override
    public void redistribute() {
        List<Station> stations = controlCenter.getStations(); // Assuming there is a getter for stations
        for (Station station : stations) {
            int idealCapacity = (int) (station.getCapacity() * IDEAL_CAPACITY_PERCENTAGE);
            int currentBikeCount = station.getNumberOfBikes();
            int bikesToMove = currentBikeCount - idealCapacity;

            // If the station has more bikes than the ideal capacity, move them to other stations
            while (bikesToMove > 0) {
                Station targetStation = selectRandomStation(stations, station);
                // Ensure the target station is not full and is not the same as the current station
                if (targetStation != null && !targetStation.IsFull() && targetStation != station) {
                    try {
                        // Select a bike to remove from the current station
                        Bike bikeToRemove = station.selectBikeForRemoval(); // You need to implement this method
                        station.TakeBike(bikeToRemove); // This line may throw BikeNotRemovableException

                        // Try to add a bike at a random space
                        int spaceToAdd = random.nextInt(targetStation.getCapacity());
                        targetStation.addBike(bikeToRemove, spaceToAdd); // This line may throw OccupiedLocationException
                        bikesToMove--;
                    } catch (BikeNotRemovableException | OccupiedLocationException e) {
                        // Handle the situation when a bike cannot be removed or the space is occupied
                        System.err.println("Failed to redistribute bike: " + e.getMessage());
                    }
                }
            }
        }
    }



    private Station selectRandomStation(List<Station> stations, Station currentStation) {
        // Find a random station that is different from the current station
        Station targetStation = null;
        do {
            targetStation = stations.get(random.nextInt(stations.size()));
        } while (targetStation == currentStation);
        
        return targetStation;
    }
}