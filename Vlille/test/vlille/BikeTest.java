package vlille;
import static org.junit.Assert.*;
import org.junit.Test;
import vlille.State;

public class BikeTest {

    @Test
    public void testGetId() {
        Bike bike = new Bike(1, true, false, new State());
        assertEquals(1, bike.getId());
    }

    @Test
    public void testHasBasket() {
        Bike bikeWithBasket = new Bike(1, true, false, new State());
        assertTrue(bikeWithBasket.hasBasket());

        Bike bikeWithoutBasket = new Bike(2, false, false, new State());
        assertFalse(bikeWithoutBasket.hasBasket());
    }

    @Test
    public void testHasLuggageRack() {
        Bike bikeWithLuggageRack = new Bike(1, true, true, new State());
        assertTrue(bikeWithLuggageRack.hasLuggageRack());

        Bike bikeWithoutLuggageRack = new Bike(2, false, false, new State());
        assertFalse(bikeWithoutLuggageRack.hasLuggageRack());
    }

    @Test
    public void testRent() {
        Bike bike = new Bike(1, true, false, new InService());

        // Test rent when the bike is in a rentable state
        try {
            bike.Rent();
            assertEquals("State should be OutOfService after renting", OutOfService.class, bike.GetState().getClass());
        } catch (Exception e) {
            fail("Rent should not throw an exception when the bike is rentable");
        }

        // Test rent when the bike is not rentable (e.g., under repair)
        bike.SetState(new UnderReparation());
        try {
            bike.Rent();
            fail("Rent should throw BikeNotRentableException when the bike is not rentable");
        } catch (BikeNotRentableException e) {
            // Expected exception
        }
    }

    @Test
    public void testDeposit() {
        Bike bike = new Bike(1, true, false, new InService());

        // Test deposit when the bike is in a depositable state
        try {
            bike.Deposit();
            assertEquals("State should be InService after depositing", InService.class, bike.GetState().getClass());
        } catch (Exception e) {
            fail("Deposit should not throw an exception when the bike is depositable");
        }

        // Test deposit when the bike is not depositable (e.g., stolen)
        bike.SetState(new Stolen());
        try {
            bike.Deposit();
            fail("Deposit should throw BikeNotReturnableException when the bike is not depositable");
        } catch (BikeNotReturnableException e) {
            // Expected exception
        }
    }

    @Test
    public void testRepair() {
        Bike bike = new Bike(1, true, false, new UnderReparation());

        // Test repair when the bike is in a repairable state
        try {
            bike.Repair();
            assertEquals("State should be InService after repairing", InService.class, bike.GetState().getClass());
        } catch (Exception e) {
            fail("Repair should not throw an exception when the bike is repairable");
        }

        // Test repair when the bike is not repairable (e.g., already in service)
        bike.SetState(new InService());
        try {
            bike.Repair();
            fail("Repair should throw BikeNotRepairableException when the bike is not repairable");
        } catch (BikeNotRepairableException e) {
            // Expected exception
        }
    }


    

}
