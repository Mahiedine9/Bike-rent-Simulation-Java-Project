package vlille;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import javax.management.timer.Timer;

public class RoundRobinStrategyTest {

    private ControlCenter controlCenter;
    private RoundRobinStrategy roundRobinStrategy;

    @Before
    public void setUp() {
        
        controlCenter = new ControlCenter(roundRobinStrategy, 5, 0, new Timer());

        // Créer une instance de RoundRobinStrategy en utilisant l'instance réelle de ControlCenter
        roundRobinStrategy = new RoundRobinStrategy(controlCenter);
    }

    @Test
    public void testRedistribute() {
        // Créer des instances réelles de Station
        Station station1 = new Station("Station1", 5);
        Station station2 = new Station("Station2", 5);
        Station station3 = new Station("Station3", 5);

        // Ajouter des stations à ControlCenter
        controlCenter.AddStation(station1);
        controlCenter.AddStation(station2);
        controlCenter.AddStation(station3);

        try {
            station1.addBike(new Bike(1, true, false, new InService()), 0);
            station1.addBike(new Bike(2, true, false, new InService()), 1);
            station1.addBike(new Bike(3, true, false, new InService()), 2);
            station1.addBike(new Bike(4, true, false, new InService()), 3);
            station1.addBike(new Bike(5, true, false, new InService()), 4);

            station2.addBike(new Bike(6, true, false, new InService()), 0);
            station2.addBike(new Bike(7, true, false, new InService()), 1);
            
        } catch (Exception e) {
            
        }
        

        // Appeler la méthode redistribute
        roundRobinStrategy.redistribute();

        // Vérifier si les vélos ont été redistribués correctement
        assertEquals(2, station1.getNumberOfBikes());
        assertEquals(4, station2.getNumberOfBikes());
        assertEquals(1, station3.getNumberOfBikes());
    }
}
