package vlille;

import org.junit.Test;
import static org.junit.Assert.*;


public class ReparatorTest {
    
    @Test
    public void testControlService() throws InterruptedException {
        // Créer un vélo en panne
        Bike bike = new Bike(1, true, false, new OutOfService());

        // Créer une instance de Reparator
        Reparator reparator = new Reparator();

        // Appeler la méthode ControlService sur le vélo
        reparator.ControlService(bike);

        // Attendre pour s'assurer que le timer a le temps de s'exécuter
        Thread.sleep(40 * 1000);  // Attendre 1 minute pour s'assurer que le timer a eu le temps de s'exécuter

        // Vérifier si le vélo est maintenant en service
        assertEquals(InService.class, bike.GetState().getClass());
    }

    




    @Test
    public void testControlServiceNoWait() {
        // Créer un vélo en panne
        Bike bike = new Bike(1, true, false, new OutOfService());

        // Créer une instance de Reparator
        Reparator reparator = new Reparator();

        // Appeler la méthode ControlService sur le vélo
        reparator.ControlService(bike);

        // Vérifier si le vélo est toujours hors service immédiatement après l'appel
        assertEquals(OutOfService.class, bike.GetState().getClass());
    }
}
