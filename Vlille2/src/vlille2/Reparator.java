package vlille;

import java.util.TimerTask;
import java.util.Timer;

/**
 * The Reparator class implements the Service interface, providing a mechanism
 * for repairing bikes in a bike sharing system. It simulates the repair process
 * and updates the bike's state to InService after the repair is complete.
 */
public class Reparator implements Service {
    
    /**
     * Initiates the repair process for a given bike. This method simulates the repair
     * by scheduling a delayed task that updates the bike's state after a certain period.
     *
     * @param bike The bike that needs to be repaired.
     */
    @Override
    public void ControlService(Bike bike) {
        
        // Inform about the start of the repair process
        System.out.println("Réparation du vélo en cours...");

        // Create a new Timer object to handle the delayed task
        Timer repairTimer = new Timer();
        
        // Schedule a new TimerTask for execution after a specified delay (5 minutes)
        repairTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                // Update the bike's state to InService once the repair duration has elapsed
                bike.SetState(new InService());
                
                // Inform about the completion of the repair process
                System.out.println("Réparation terminée. Le vélo est maintenant en service.");
                
                // Cancel the timer to clean up resources
                repairTimer.cancel();
            }
        }, 5 * 60 * 1000);  // Set the delay for the task (300000 milliseconds or 5 minutes)
    }
}

