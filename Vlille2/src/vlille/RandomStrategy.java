import java.util.List;
import java.util.Random;

public class RandomStrategy implements RedistributionStrategy {

    private ControlCenter controlCenter;
    private Random random = new Random();
    private final double IDEAL_CAPACITY_PERCENTAGE = 0.75; // 75% of total capacity as ideal

    public RandomStrategy(ControlCenter controlCenter) {
        this.controlCenter = controlCenter;
    }

    @Override
    public void redistribute() {
        List<Station> stations = controlCenter.getAllStations();

        for (Station station : stations) {
            int idealCapacity = (int) (station.getCapacity() * IDEAL_CAPACITY_PERCENTAGE);
            int bikesToMove = station.getBikeCount() - idealCapacity;

            if (bikesToMove > 0) {
                for (int i = 0; i < bikesToMove; i++) {
                    // Choose a random station to move a bike to
                    Station targetStation = stations.get(random.nextInt(stations.size()));
                    Bike bike = station.removeBike();
                    if (bike != null) {
                        targetStation.addBike(bike);
                    }
                }
            }
        }
    }

}
