package vlille;

import java.util.Random;
import java.util.List;

public class RoundRobinStrategy implements RedistributionStrategy {

    private ControlCenter controlCenter;

    public RoundRobinStrategy(ControlCenter controlCenter) {
        this.controlCenter = controlCenter;
    }
    
    @Override
    public void redistribute() {
        List<Station> stations = controlCenter.getAllStations();
        int totalBikesNeeded = calculateTotalBikesNeeded(stations);

        for (Station station : stations) {
            int bikesNeeded = station.getCapacity() - station.getBikeCount();
            if (bikesNeeded > 0 && totalBikesNeeded > 0) {
                dispatchBikesToStation(station, bikesNeeded);
                totalBikesNeeded -= bikesNeeded;
            }
        }
    }

    private int calculateTotalBikesNeeded(List<Station> stations) {
        int total = 0;
        for (Station station : stations) {
            total += station.getCapacity() - station.getBikeCount();
        }
        return total;
    }

    private void dispatchBikesToStation(Station station, int count) {
        for (int i = 0; i < count; i++) {
            // Generate a random bike ID (or any other unique identifier)
            String bikeId = generateRandomBikeId();

            // Create a new bike with random parameters
            Bike bike = new Bike(bikeId);

            // Add the bike to the station
            station.addBike(bike);
        }
    }

    private String generateRandomBikeId() {
        Random random = new Random();
        // Generate a random ID; this is a basic example, adjust as needed
        return "Bike-" + random.nextInt(10000);
    }
}

