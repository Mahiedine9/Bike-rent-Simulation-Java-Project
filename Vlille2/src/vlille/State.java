package vlille;

import Exceptions.BikeNotRepairableException;
import Exceptions.BikeNotRentableException;
import Exceptions.BikeNotReturnableException;

public abstract class State {
    public abstract void repair(Bike bike) throws BikeNotRepairableException;
    public abstract void rent(Bike bike) throws BikeNotRentableException;
    public abstract void deposit(Bike bike) throws BikeNotReturnableException;
}
