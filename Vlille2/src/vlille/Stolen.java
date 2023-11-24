package vlille;

import Exceptions.BikeNotRepairableException;
import Exceptions.BikeNotRentableException;
import Exceptions.BikeNotReturnableException;

public class Stolen extends State {
    @Override
    public void repair(Bike bike) throws BikeNotRepairableException {
        throw new BikeNotRepairableException("Bike is stolen and cannot be repaired.");
    }

    @Override
    public void rent(Bike bike) throws BikeNotRentableException {
        throw new BikeNotRentableException("Bike is stolen and cannot be rented.");
    }

    @Override
    public void deposit(Bike bike) throws BikeNotReturnableException {
        throw new BikeNotReturnableException("Bike is stolen and cannot be deposited.");
    }
}
