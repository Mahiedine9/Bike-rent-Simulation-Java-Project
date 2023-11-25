package vlille;


public class Bike implements Transport{
    private int id;
    private boolean hasBasket;
    private boolean hasLuggageRack;
    private int rentalCount;
    private final int rentalMax = 10; // This can be set to whatever max value is appropriate
    private State currentState;


    // Constructor
    public Bike(int id, boolean hasBasket, boolean hasLuggageRack, State state) {
        this.id = id;
        this.hasBasket = hasBasket;
        this.hasLuggageRack = hasLuggageRack;
        this.rentalCount = 0;
        this.currentState = state; // Assuming the bike starts in an InService state

    }

    // Setters and Getters
    public void SetState(State newState) {
        this.currentState = newState;
    }

    public State GetState() {
        return this.currentState;
    }

    public int GetId() {
        return this.id;
    }

    public boolean HasBasket() {
        return this.hasBasket;
    }

    public boolean HasLuggageRack() {
        return this.hasLuggageRack;
    }

    public int GetRentalCount() {
        return this.rentalCount;
    }

    public void SetRentalCount(int rentalCount) {
        this.rentalCount = rentalCount;
    }

 

}
