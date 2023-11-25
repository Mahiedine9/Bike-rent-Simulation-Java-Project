package vlille;

import exceptions.BikeNotRepairableException;
import exceptions.BikeNotRentableException;
import exceptions.BikeNotReturnableException;


public abstract class State {

    public abstract void Repair(Transport bike) throws BikeNotRepairableException;
    public abstract void Rent(Transport bike) throws BikeNotRentableException;
    public abstract void Deposit(Transport bike) throws BikeNotReturnableException;
}
