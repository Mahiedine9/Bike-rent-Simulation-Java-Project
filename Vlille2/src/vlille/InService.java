package vlille;

import Exceptions.BikeNotRentableException;
import Exceptions.BikeNotReturnableException;
import Exceptions.BikeNotRepairableException;


public class InService extends State {
    @Override
    public void repair(Bike bike) throws BikeNotRepairableException {
        throw new BikeNotRepairableException("Bike is in service and cannot be repaired.");
    }

    @Override
    public void rent(Bike bike) throws BikeNotRentableException {
        // Logic to rent the bike.
        bike.setRentalCount(bike.getRentalCount() + 1);

        if (bike.getRentalCount() >= Bike.RentalMax) {
            // If the bike has reached its maximum rentals, it goes out of service for maintenance.
            bike.setState(new OutOfService());
        }
    }

    @Override
    public void deposit(Bike bike) throws BikeNotReturnableException {
        // TO DO 
    }
}
