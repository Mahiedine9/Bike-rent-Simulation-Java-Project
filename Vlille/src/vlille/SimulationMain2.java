package vlille;

import java.util.ArrayList;
import java.util.Random;
import exceptions.*;

public class SimulationMain2 {
    public static void main(String[] args) {
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

            // Optionally, add more complex simulation behavior here

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
}
