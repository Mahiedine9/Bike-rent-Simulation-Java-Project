package vlille;

import java.util.List;
import java.util.Random;
import exceptions.*;

public class RandomStrategy implements RedistributionStrategy {

    private ControlCenter controlCenter;
    private Random random = new Random();
    private final double IDEAL_CAPACITY_PERCENTAGE = 0.75; 

    public RandomStrategy(ControlCenter controlCenter) {
        this.controlCenter = controlCenter;
    }

    public void SetControlCenter(ControlCenter control){
        this.controlCenter = control;
    } 

    
    @Override
    public void redistribute() {
        List<Station> stations = controlCenter.getStations(); 
        for (Station station : stations) {
            int idealCapacity = (int) (station.getCapacity() * IDEAL_CAPACITY_PERCENTAGE);
            int currentBikeCount = station.getNumberOfBikes();
            int bikesToMove = currentBikeCount - idealCapacity;

            while (bikesToMove > 0) {
                int redistributionAttempts = 0;
                while (redistributionAttempts < stations.size()) {
                    Station targetStation = selectRandomStation(stations, station);
                    if (targetStation != null && !targetStation.IsFull() && targetStation != station) {
                        try {
                            Bike bikeToRemove = station.selectBikeForRemoval(); 
                            station.TakeBike(bikeToRemove); 
                            int spaceToAdd = random.nextInt(targetStation.getCapacity());
                            targetStation.addBike(bikeToRemove, spaceToAdd); 
                            bikesToMove--;
                            break;
                        } catch (BikeNotRemovableException | OccupiedLocationException e) {
                            System.err.println("Failed to redistribute bike: " + e.getMessage());
                        }
                    }
                    redistributionAttempts++;
                }
                if (redistributionAttempts >= stations.size()) {
                    // Unable to redistribute this bike, move to the next
                    break;
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
