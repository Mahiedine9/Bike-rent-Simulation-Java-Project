package vlille;

import Exceptions.BikeNotRentableException;
import Exceptions.BikeNotReturnableException;
import Exceptions.BikeNotRepairableException;

/**
 * This class represents the "In Service" state of a bike in a bike sharing system.
 * When a bike is in service, it can be rented and deposited. However, it cannot be repaired.
 */
public class InService extends State {

    /**
     * Throws a BikeNotRepairableException as a bike in service cannot be repaired.
     *
     * @param bike The bike in service.
     * @throws BikeNotRepairableException Thrown when attempting to repair a bike in service.
     */
    @Override
    public void Repair(Transport bike) throws BikeNotRepairableException {
        throw new BikeNotRepairableException("Bike is in service and cannot be repaired.");
    }

    /**
     * Rents the bike, updating its rental count. If the bike reaches its maximum rentals,
     * it goes out of service for maintenance.
     *
     * @param bike The bike to be rented.
     * @throws BikeNotRentableException Thrown when attempting to rent a bike in service.
     */
    @Override
    public void Rent(Transport bike) throws BikeNotRentableException {
        bike.UpdateRentalCount();
        if (bike.getRentalCount() == Bike.RentalMax) {
            bike.SetState(new OutOfService());
        }
    }

    /**
     * Deposits the bike into a station if the station is not full.
     *
     * @param bike The bike to be deposited.
     * @param station The station where the bike is deposited.
     * @throws BikeNotReturnableException Thrown when attempting to deposit a bike into a full station.
     */
    @Override
    public void Deposit(Transport bike, Station station) throws BikeNotReturnableException {
        if (station.IsFull()) {
            throw new StationFull("Station is full, bike couldn't be added.");
        }
        station.AddBike(bike);
    }
}
