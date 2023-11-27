package vlille;

import org.junit.Test;
import static org.junit.Assert.*;

public class BikeTest {

    @Test
    public void testGetId() {
        Bike bike = new Bike(1, true, false, new InService());
        assertEquals(1, bike.GetId());
    }

    @Test
    public void testHasBasket() {
        Bike bikeWithBasket = new Bike(2, true, false, new InService());
        assertTrue(bikeWithBasket.HasBasket());

        Bike bikeWithoutBasket = new Bike(3, false, false, new InService());
        assertFalse(bikeWithoutBasket.HasBasket());
    }

    @Test
    public void testHasLuggageRack() {
        Bike bikeWithRack = new Bike(4, true, true, new InService());
        assertTrue(bikeWithRack.HasLuggageRack());

        Bike bikeWithoutRack = new Bike(5, false, false, new InService());
        assertFalse(bikeWithoutRack.HasLuggageRack());
    }

    @Test
    public void testGetRentalCount() {
        Bike bike = new Bike(6, true, false, new InService());
        assertEquals(0, bike.GetRentalCount());

        // Assuming UpdateRentalCount increments the count
        bike.UpdateRentalCount();
        assertEquals(1, bike.GetRentalCount());
    }

    @Test
    public void testGetRentalMax() {
        Bike bike = new Bike(7, true, false, new InService());
        assertEquals(10, bike.GetRentalMax());
    }

    @Test
    public void testSetStateAndGetState() {
        Bike bike = new Bike(8, true, false, new InService());
        State newState = new OutOfService();
        bike.SetState(newState);
        assertEquals(newState, bike.GetState());
    }


} 

