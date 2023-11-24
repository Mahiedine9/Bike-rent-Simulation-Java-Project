package vlille;

import Exceptions.BikeNotRepairableException;
import Exceptions.BikeNotRentableException;
import Exceptions.BikeNotReturnableException;

public class OutOfService extends State {
    @Override
    public void repair(Bike bike) {
        // Logic to repair the bike
        bike.setState(new InService());
    }

    @Override
    public void rent(Bike bike) throws BikeNotRentableException {
        throw new BikeNotRentableException("Bike is out of service and cannot be rented.");
    }

    @Override
    public void deposit(Bike bike) throws BikeNotReturnableException {
        throw new BikeNotReturnableException("Bike is out of service and cannot be deposited.");
    }
}

