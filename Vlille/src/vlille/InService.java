package vlille;

import exceptions.*;
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
    public void Repair(Bike bike) throws BikeNotRepairableException {
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
    public void Rent(Bike bike) throws BikeNotRentableException {
        bike.UpdateRentalCount();
        if (bike.GetRentalCount() == bike.GetRentalMax()) {
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
    public void Deposit(Bike bike, Station station, int space) throws BikeNotReturnableException {
        if (station.IsFull()) {
            throw new BikeNotReturnableException("Station is full, bike couldn't be added.");
        }
        try{
            station.AddBike(bike, space);

        }catch (Exception e) {
            throw new BikeNotReturnableException("La place est déjà occupée par un autre vélo.");
        }
            
          
    }


    @Override
    public void Take(Bike bike, Station station) throws BikeNotRemovableException{
        try {
            station.TakeBike(bike);
        } catch (Exception e) {
            throw new BikeNotRemovableException("Bike cannot be removed.");
        }
        
    }


}
