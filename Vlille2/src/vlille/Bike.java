package vlille;

import Exceptions.BikeNotRepairableException;
import Exceptions.BikeNotRentableException;
import Exceptions.BikeNotReturnableException;

public class Bike {
    private int id;
    private boolean hasBasket;
    private boolean hasLuggageRack;
    private int rentalCount;
    private final int RentalMax = 10; // This can be set to whatever max value is appropriate
    private State currentState;

    // Constructor
    public Bike(int id, boolean hasBasket, boolean hasLuggageRack) {
        this.id = id;
        this.hasBasket = hasBasket;
        this.hasLuggageRack = hasLuggageRack;
        this.rentalCount = 0;
        this.currentState = new InService(); // Assuming the bike starts in an InService state
    }

    // Setters and Getters
    public void setState(State newState) {
        this.currentState = newState;
    }

    public State getState() {
        return currentState;
    }

    public int getId() {
        return id;
    }

    public boolean hasBasket() {
        return hasBasket;
    }

    public boolean hasLuggageRack() {
        return hasLuggageRack;
    }

    public int getRentalCount() {
        return rentalCount;
    }

    public void setRentalCount(int rentalCount) {
        this.rentalCount = rentalCount;
    }

    // Delegating State-specific behaviors
    public void repair() throws BikeNotRepairableException {
        currentState.repair(this);
    }

    public void rent() throws BikeNotRentableException {
        currentState.rent(this);
    }

    public void deposit() throws BikeNotReturnableException {
        currentState.deposit(this);
    }

    // Additional methods can be added as per project requirements
}
