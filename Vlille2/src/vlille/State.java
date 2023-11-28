package vlille;

import exceptions.*;



public abstract class State{

    public abstract void Repair(Bike bike, Service reparator) throws BikeNotRepairableException;
    public abstract void Rent(Bike bike) throws BikeNotRentableException;
    public abstract void Deposit(Bike bike, Station station, int space) throws BikeNotReturnableException;
    public abstract void Take(Bike bike, Station station) throws BikeNotRemovableException;
}
