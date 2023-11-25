package vlille;

import Exceptions.BikeNotRentableException;
import Exceptions.BikeNotReturnableException;

/**
 * This class represents the "Under Repair" state of a bike in a bike sharing system.
 * When a bike is under repair, it cannot be rented or deposited, and it transitions
 * to the "In Service" state after repair.
 */
public class UnderReparation extends State {

    /**
     * Repairs the bike, transitioning it to the "In Service" state.
     * Additional logic for completing the repair can be added here.
     *
     * @param bike The bike to be repaired.
     */
    @Override
    public void Repair(Transport bike) {
        throw new BikeAlreadyUnderRepair("Bike is already under repair and cannot be repaired")
    }

    /**
     * Throws a BikeNotRentableException as a bike under repair cannot be rented.
     *
     * @param bike The bike attempting to be rented.
     * @throws BikeNotRentableException Thrown when attempting to rent a bike under repair.
     */
    @Override
    public void Rent(Transport bike) throws BikeNotRentableException {
        throw new BikeNotRentableException("Bike is currently under repair and cannot be rented.");
    }

    /**
     * Throws a BikeNotReturnableException as a bike under repair cannot be deposited.
     *
     * @param bike The bike attempting to be deposited.
     * @throws BikeNotReturnableException Thrown when attempting to deposit a bike under repair.
     */
    @Override
    public void Deposit(Transport bike) throws BikeNotReturnableException {
        throw new BikeNotReturnableException("Bike is currently under repair and cannot be deposited.");
    }
}
