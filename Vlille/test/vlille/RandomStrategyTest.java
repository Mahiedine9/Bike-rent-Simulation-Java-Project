package vlille;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class RandomStrategyTest {

    private ControlCenter controlCenter;
    private RandomStrategy randomStrategy;

    @Before
    public void setUp() {
        controlCenter = new ControlCenter(randomStrategy, 5);
        randomStrategy = new RandomStrategy(controlCenter);
    }

    @Test
    public void testRedistribute() {
        Station station1 = new Station("Station1", 5);
        Station station2 = new Station("Station2", 5);
        Station station3 = new Station("Station3", 5);

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
        randomStrategy.redistribute();

        assertEquals(7, station1.getNumberOfBikes()+ station2.getNumberOfBikes() +station3.getNumberOfBikes()); // Mettez le nombre attendu ici
       
    }
}
