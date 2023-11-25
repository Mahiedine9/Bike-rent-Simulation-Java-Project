package vlille;

import Exceptions.BikeNotRentableException;
import Exceptions.BikeNotReturnableException;
import Exceptions.BikeNotRepairableException;

/**
 * This class represents the "Out of Service" state of a bike in a bike sharing system.
 * When a bike is out of service, it cannot be rented or deposited until it is repaired.
 */
public class OutOfService extends State {

    /**
     * Repairs the bike, transitioning it to the "In Service" state.
     *
     * @param bike The bike to be repaired.
     */
    @Override
    public void Repair(Transport bike) {
        // Logic to repair the bike
        bike.setState(new InService());
    }

    /**
     * Throws a BikeNotRentableException as a bike out of service cannot be rented.
     *
     * @param bike The bike attempting to be rented.
     * @throws BikeNotRentableException Thrown when attempting to rent a bike out of service.
     */
    @Override
    public void Rent(Transport bike) throws BikeNotRentableException {
        throw new BikeNotRentableException("Bike is out of service and cannot be rented.");
    }

    /**
     * Throws a BikeNotReturnableException as a bike out of service cannot be deposited.
     *
     * @param bike The bike attempting to be deposited.
     * @throws BikeNotReturnableException Thrown when attempting to deposit a bike out of service.
     */
    @Override
    public void Deposit(Transport bike) throws BikeNotReturnableException {
        throw new BikeNotReturnableException("Bike is out of service and cannot be deposited.");
    }
}
