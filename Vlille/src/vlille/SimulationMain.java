package vlille;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import exceptions.BikeNotRentableException;
import exceptions.OccupiedLocationException;

public class SimulationMain {

    public static void main(String[] args) throws OccupiedLocationException {
        
        RandomStrategy randomStrategy = new RandomStrategy(null);
        ControlCenter controlCenter = new ControlCenter(randomStrategy, 5);
        randomStrategy.SetControlCenter(controlCenter);
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.print("Combien de stations voulez-vous créer ? ");
        int nbOfStations = scanner.nextInt();
        
        for (int i = 0; i < nbOfStations; i++) {
            Station station = new Station("station " + i, 10);
            for (int j = 0; j < station.getCapacity(); j++) {
                station.addBike(new Bike(j, random.nextBoolean(), random.nextBoolean(), new InService()), j);
            }
            controlCenter.AddStation(station);
        }

        while (true) {
            List<Station> stations = controlCenter.getStations();
            System.out.println("Liste des stations : ");
            for (int i = 0; i < stations.size(); i++) {
                System.out.println((i + 1) + ". " + stations.get(i).getName());
            }

            // Demander à l'utilisateur de choisir une station
            System.out.println("Choisissez une station : ");
            int choixStation = scanner.nextInt();
            Station station = stations.get(choixStation - 1);

            // Afficher les vélos disponibles dans la station
            List<Bike> bikes = station.getBikes();
            System.out.println("Vélos disponibles dans la station " + station.getName() + " : ");
            for (int i = 0; i < bikes.size(); i++) {
                System.out.println((i + 1) + ". Vélo " + bikes.get(i).GetId());
            }

            // Demander à l'utilisateur de choisir un vélo
            System.out.println("Choisissez un vélo : ");
            int choixVelo = scanner.nextInt();
            Bike bike = bikes.get(choixVelo - 1);

            // Demander à l'utilisateur s'il veut louer le vélo
            System.out.println("Voulez-vous louer le vélo " + bike.GetId() + " ? (oui/non)");
            String choixLouer = scanner.next();

            if (choixLouer.equalsIgnoreCase("oui")) {
                try {
                    bike.GetState().Rent(bike);
                } catch (BikeNotRentableException e) {
                    System.out.println("le vélo ne peut pas etre louer pour l'instant");
                    e.printStackTrace();
                }
                System.out.println("Vous avez loué le vélo " + bike.GetId() + " !");
            } else if (choixLouer.equalsIgnoreCase("non")) {
                
                System.out.println("Vous n'avez pas loué de vélo.");
            } else {
                System.out.println("Choix non reconnu. Veuillez répondre par 'oui' ou 'non'.");
            }

            

        }
    } 
}
