package vlille;

/**
 * Represents an electric bike in a bike sharing system, extending the base Bike class.
 */
public class ElectricBike extends Bike {

    /**
     * Constructs an ElectricBike object.
     *
     * @param id              The unique identifier of the bike.
     * @param hasBasket       True if the bike has a basket; otherwise, false.
     * @param hasLuggageRack  True if the bike has a luggage rack; otherwise, false.
     * @param state           The initial state of the bike (e.g., InService, OutOfService).
     */
    public ElectricBike(int id, boolean hasBasket, boolean hasLuggageRack, State state) {
        super(id, hasBasket, hasLuggageRack, state);
        // Additional initialization specific to ElectricBike if needed
    }

}
