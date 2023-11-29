package vlille;


public interface RedistributionStrategy {

    /**
     * Method to redistribute bikes among a set of stations.
     * 
     * @param stations The list or map of stations to consider for redistribution.
     */
    void redistribute();

    /**
     * Any other methods that are common across all redistribution strategies can be declared here.
     */
}