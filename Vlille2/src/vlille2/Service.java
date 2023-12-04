package vlille;

/**
 * The Service interface defines a contract for services in the bike-sharing system.
 * This interface can be implemented by classes that provide various types of services,
 * such as repairing or maintaining bikes.
 */
public interface Service {

    /**
     * Initiates a service operation on the provided bike. The specific nature of the service
     * (e.g., repair, maintenance) is determined by the implementing class.
     *
     * Implementing this method allows different types of service operations to be performed
     * on bikes, depending on the needs of the bike-sharing system. For example, a class
     * implementing this interface could use it to repair bikes, perform maintenance checks,
     * or any other service-related task.
     *
     * @param bike The bike to be serviced.
     */
    public abstract void ControlService(Bike bike);
}
