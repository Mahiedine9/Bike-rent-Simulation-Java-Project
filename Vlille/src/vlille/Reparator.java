package vlille;

import java.util.TimerTask;
import java.util.Timer;

public class Reparator implements Service{
    
    
    @Override
    public void ControlService(Bike bike) {
        
        System.out.println("Réparation du vélo en cours...");

        // Planifier la tâche de remise en service après 5min
        Timer repairTimer = new Timer();
        repairTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                // Changer l'état du vélo à InService après l'intervalle
                bike.SetState(new InService());
                System.out.println("Réparation terminée. Le vélo est maintenant en service.");
                // Annuler le timer après la tâche
                repairTimer.cancel();
            }
        }, 5 * 60 * 1000);  // Intervalle en millisecondes (5 minutes dans cet exemple)
    }
    
}
