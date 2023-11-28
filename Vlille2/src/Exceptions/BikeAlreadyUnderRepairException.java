package exceptions;

public class BikeAlreadyUnderRepairException extends Exception {
    public BikeAlreadyUnderRepairException(String message) {
        super(message);
    }
}