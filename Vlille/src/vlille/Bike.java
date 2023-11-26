package vlille;



/**
 * Represents a bike in a bike sharing system.
 */
public class Bike implements Transport {
    private int id;
    private boolean hasBasket;
    private boolean hasLuggageRack;
    private int rentalCount;
    private final int rentalMax = 10; // This can be set to whatever max value is appropriate
    private State currentState;

    /**
     * Constructs a Bike object.
     *
     * @param id              The unique identifier of the bike.
     * @param hasBasket       True if the bike has a basket; otherwise, false.
     * @param hasLuggageRack  True if the bike has a luggage rack; otherwise, false.
     * @param state           The initial state of the bike (e.g., InService, OutOfService).
     */
    public Bike(int id, boolean hasBasket, boolean hasLuggageRack, State state) {
        this.id = id;
        this.hasBasket = hasBasket;
        this.hasLuggageRack = hasLuggageRack;
        this.rentalCount = 0;
        this.currentState = state; // Assuming the bike starts in an InService state
    }

    /**
     * Sets the current state of the bike.
     *
     * @param newState The new state of the bike.
     */
    public void SetState(State newState) {
        this.currentState = newState;
    }

    /**
     * Gets the current state of the bike.
     *
     * @return The current state of the bike.
     */
    public State GetState() {
        return this.currentState;
    }

    /**
     * Gets the unique identifier of the bike.
     *
     * @return The unique identifier of the bike.
     */
    public int GetId() {
        return this.id;
    }

    /**
     * Checks if the bike has a basket.
     *
     * @return True if the bike has a basket; otherwise, false.
     */
    public boolean HasBasket() {
        return this.hasBasket;
    }

    /**
     * Checks if the bike has a luggage rack.
     *
     * @return True if the bike has a luggage rack; otherwise, false.
     */
    public boolean HasLuggageRack() {
        return this.hasLuggageRack;
    }

    /**
     * Gets the number of times the bike has been rented.
     *
     * @return The rental count of the bike.
     */
    public int GetRentalCount() {
        return this.rentalCount;
    }

    /**
     * Updates the rental count of the bike.
     *
     * @param rentalCount The new rental count.
     */
    public void UpdateRentalCount() {
        this.rentalCount++;
    }

    public int GetRentalMax(){
        return this.rentalMax;
    } 

    @Override
    public String toString() {
        return "Bike [id=" + id + ", hasBasket=" + hasBasket + ", hasLuggageRack=" + hasLuggageRack + ", rentalCount="
                + rentalCount + ", rentalMax=" + rentalMax + ", currentState=" + currentState + "]";
    }

}
