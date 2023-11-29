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
                    station.addBike(bike, station.findEmptySlot());
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
            if (station.getNumberOfBikes() > station.getCapacity()) {
                while (station.getNumberOfBikes() > station.getCapacity()) {
                    try {
                        Bike bikeToRemove = station.selectBikeForRemoval();
                        if (bikeToRemove != null) {
                            station.TakeBike(bikeToRemove);
                            bikes.add(bikeToRemove);
                        }
                    } catch (BikeNotRemovableException e) {
                        // Gérer l'exception, par exemple, passer au vélo suivant ou sortir de la boucle
                        e.printStackTrace();
                        break; // Sortir de la boucle en cas d'erreur
                    }
                }
            }
        }
        return bikes;
    }
    
}
