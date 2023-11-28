package vlille;

import org.junit.Test;
import static org.junit.Assert.*;
import exceptions.*;

public class InServiceTest {

    @Test(expected = BikeNotRepairableException.class)
    public void testRepair() throws BikeNotRepairableException {
        Service reparator = new Reparator();
        State state = new InService();
        Bike bike = new Bike(1, true, false, state);

        state.Repair(bike, reparator);
    }

    @Test
    public void testRent() {
        State state = new InService();
        Bike bike = new Bike(1, true, false, state);

        try {
            state.Rent(bike);
        } catch (BikeNotRentableException e) {
            fail("Should not throw BikeNotRentableException");
        }

        assertEquals(InService.class, bike.GetState().getClass());
        assertEquals(1, bike.GetRentalCount());

        for (int i = 0; i < 10; i++) {
            try {
                state.Rent(bike);
            } catch (BikeNotRentableException e) {
                fail("Should not throw BikeNotRentableException");
            }
        }
        assertEquals(OutOfService.class, bike.GetState().getClass());
    }

    /**
     * @throws BikeNotReturnableException
     */
    @Test
    public void testDeposit() {
        State state = new InService();
        Bike bike = new Bike(1, true, false, state);
        Station station = new Station("1", 10);

        try {
           state.Deposit(bike, station, 0);
            // No exception should be thrown
        } catch (BikeNotReturnableException e) {
           fail("Unexpected BikeNotReturnableException: " + e.getMessage());
        }

    }
    
    
    @Test
    public void testTake() {
        State state = new InService();
        Bike bike = new Bike(1, true, false, state);
        Station station = new Station("1", 10);

        try {
           state.Take(bike, station);
           
        } catch (BikeNotRemovableException e) {
        }

    }

}
