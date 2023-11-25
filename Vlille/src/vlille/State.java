package vlille;

import exceptions.BikeNotRepairableException;
import exceptions.BikeNotRentableException;
import exceptions.BikeNotReturnableException;


public abstract class State {

    public abstract void repair(Transport bike) throws BikeNotRepairableException;
    public abstract void rent(Transport bike) throws BikeNotRentableException;
    public abstract void deposit(Transport bike) throws BikeNotReturnableException;
}
