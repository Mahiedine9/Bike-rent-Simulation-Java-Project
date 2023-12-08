package vlille;


public interface RedistributionStrategy {

    /**
     * Method to redistribute bikes among a set of stations.
     * 
     * 
     */
    void redistribute();

    /**
     * Any other methods that are common across all redistribution strategies can be declared here.
     */
}