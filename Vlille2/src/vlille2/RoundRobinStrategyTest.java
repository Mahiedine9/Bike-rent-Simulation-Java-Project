package vlille;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import exceptions.*;

import javax.management.timer.Timer;

public class RoundRobinStrategyTest {

    private ControlCenter controlCenter;
    private RoundRobinStrategy roundRobinStrategy;
    private Station station1, station2, station3;

    @Before
    public void setUp() {
        ControlCenter tempControlCenter = new ControlCenter(null, 5, 0); 
        roundRobinStrategy = new RoundRobinStrategy(tempControlCenter);

        controlCenter = new ControlCenter(roundRobinStrategy, 5, 0);

        
        roundRobinStrategy.setControlCenter(controlCenter);

        // Add stations to ControlCenter
        station1 = new Station("Station1", 5); // This station will be over capacity
        station2 = new Station("Station2", 5); // This station will be under capacity
        station3 = new Station("Station3", 5); // This station will have no bikes initially

        controlCenter.AddStation(station1);
        controlCenter.AddStation(station2);
        controlCenter.AddStation(station3);

        // Overfill station1 and underfill station2
        try {
            for (int i = 1; i <= 7; i++) { // Adding 7 bikes to station1 (over capacity)
                station1.addBike(new Bike(i, true, false, new InService()), station1.findEmptySlot());
            }
            for (int i = 8; i <= 9; i++) { // Adding 2 bikes to station2 (under capacity)
                station2.addBike(new Bike(i, true, false, new InService()), station2.findEmptySlot());
            }
        } catch (OccupiedLocationException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRedistribute() {
        // Redistribute bikes
        roundRobinStrategy.redistribute();

        // Verify the redistribution outcome
        assertEquals(5, station1.getNumberOfBikes()); // Expect 5 bikes after redistribution
        assertEquals(3, station2.getNumberOfBikes()); // Expect 3 bikes after redistribution
        assertEquals(1, station3.getNumberOfBikes()); // Expect 1 bike after redistribution
    }
}
