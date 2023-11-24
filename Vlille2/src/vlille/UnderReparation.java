package vlille;

import Exceptions.BikeNotRepairableException;
import Exceptions.BikeNotRentableException;
import Exceptions.BikeNotReturnableException;

public class UnderReparation extends State {
    @Override
    public void repair(Bike bike) {
        bike.setState(new InService());
        // Additional logic for completing the repair
    }

    @Override
    public void rent(Bike bike) throws BikeNotRentableException {
        throw new BikeNotRentableException("Bike is currently under repair and cannot be rented.");
    }

    @Override
    public void deposit(Bike bike) throws BikeNotReturnableException {
        throw new BikeNotReturnableException("Bike is currently under repair and cannot be deposited.");
    }
}
