package vlille;
import static org.junit.Assert.*;
import org.junit.Test;

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

}
