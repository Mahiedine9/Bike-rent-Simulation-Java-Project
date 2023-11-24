

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class ControlCenter {
    private Map<String, Station> stations;

    // Constructor
    public ControlCenter() {
        this.stations = new HashMap<>();
    }

    // Add a station to the control center
    public void addStation(Station station) {
        stations.put(station.getStationId(), station);
        System.out.println("Station " + station.getStationId() + " added to control center.");
    }

    // Remove a station from the control center
    public void removeStation(String stationId) {
        if (stations.containsKey(stationId)) {
            stations.remove(stationId);
            System.out.println("Station " + stationId + " removed from control center.");
        } else {
            System.out.println("Station " + stationId + " not found in control center.");
        }
    }

    // Get a station by ID
    public Station getStation(String stationId) {
        return stations.get(stationId);
    }

    // Monitor stations (placeholder for more complex logic)
    public void monitorStations() {
        // Implement logic to monitor and manage stations
        System.out.println("Monitoring all stations.");
    }

    public List<Station> getAllStations() {
        return new ArrayList<>(stations.values());
    }

}

