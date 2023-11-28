package vlille;

import org.junit.Test;
import exceptions.*;

public class UnderReparationTest {

    @Test(expected = BikeNotRepairableException.class)
    public void testRepairException() throws BikeNotRepairableException {
        Service reparator = new Reparator();
        State state = new UnderReparation();
        Bike bike = new Bike(1, true, false, state);

        state.Repair(bike, reparator);
    }

    @Test(expected = BikeNotRentableException.class)
    public void testRentException() throws BikeNotRentableException {
        State state = new UnderReparation();
        Bike bike = new Bike(1, true, false, state);

        state.Rent(bike);
    }

    @Test(expected = BikeNotReturnableException.class)
    public void testDepositException() throws BikeNotReturnableException {
        State state = new UnderReparation();
        Bike bike = new Bike(1, true, false, state);
        Station station = new Station("1", 10);

        state.Deposit(bike, station, 0);
    }

    @Test(expected = BikeNotRemovableException.class)
    public void testTakeException() throws BikeNotRemovableException {
        State state = new UnderReparation();
        Bike bike = new Bike(1, true, false, state);
        Station station = new Station("1", 10);

        state.Take(bike, station);
    }
}
