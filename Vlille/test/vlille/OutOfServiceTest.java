package vlille;

import org.junit.Test;
import static org.junit.Assert.*;
import exceptions.*;

public class OutOfServiceTest {

    @Test
    public void testRepair() throws BikeNotRepairableException{
        Service reparator = new Reparator();
        State state = new OutOfService();
        Bike bike = new Bike(1, true, false, state);

        state.Repair(bike, reparator);

        assertEquals(InService.class, bike.GetState().getClass());
    }

    @Test(expected = BikeNotRentableException.class)
    public void testRentException() throws BikeNotRentableException {
        State state = new OutOfService();
        Bike bike = new Bike(1, true, false, state);

        state.Rent(bike);
    }

    @Test(expected = BikeNotReturnableException.class)
    public void testDepositException() throws BikeNotReturnableException {
        State state = new OutOfService();
        Bike bike = new Bike(1, true, false, state);
        Station station = new Station("1", 10);

        state.Deposit(bike, station, 0);
    }

    @Test(expected = BikeNotRemovableException.class)
    public void testTakeException() throws BikeNotRemovableException {
        State state = new OutOfService();
        Bike bike = new Bike(1, true, false, state);
        Station station = new Station("1", 10);

        state.Take(bike, station);
    }
}
