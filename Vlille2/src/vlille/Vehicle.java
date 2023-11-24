public interface Vehicle {

    void rentVehicle();

    void returnVehicle();

    boolean isAvailable();

    void setState(State newState);

    State getState();

    // Additional methods can be added here if needed
}
