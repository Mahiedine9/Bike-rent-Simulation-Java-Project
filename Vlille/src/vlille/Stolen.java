package vlille;

import Exceptions.BikeNotRepairableException;
import Exceptions.BikeNotRentableException;
import Exceptions.BikeNotReturnableException;

/**
 * This class represents the "Stolen" state of a bike in a bike sharing system.
 * When a bike is stolen, it cannot be repaired, rented, or deposited.
 */
public class Stolen extends State {

    /**
     * Throws a BikeNotRepairableException as a stolen bike cannot be repaired.
     *
     * @param bike The stolen bike.
     * @throws BikeNotRepairableException Thrown when attempting to repair a stolen bike.
     */
    @Override
    public void Repair(Transport bike) throws BikeNotRepairableException {
        throw new BikeNotRepairableException("Bike is stolen and cannot be repaired.");
    }

    /**
     * Throws a BikeNotRentableException as a stolen bike cannot be rented.
     *
     * @param bike The stolen bike.
     * @throws BikeNotRentableException Thrown when attempting to rent a stolen bike.
     */
    @Override
    public void Rent(Transport bike) throws BikeNotRentableException {
        throw new BikeNotRentableException("Bike is stolen and cannot be rented.");
    }

    /**
     * Throws a BikeNotReturnableException as a stolen bike cannot be deposited.
     *
     * @param bike The stolen bike.
     * @throws BikeNotReturnableException Thrown when attempting to deposit a stolen bike.
     */
    @Override
    public void Deposit(Transport bike) throws BikeNotReturnableException {
        throw new BikeNotReturnableException("Bike is stolen and cannot be deposited.");
    }
}
