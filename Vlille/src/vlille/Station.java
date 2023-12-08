package vlille;
import java.util.ArrayList;
import java.util.List;
import exceptions.*;

public class Station {

   /** array of slots for bikes in the station */
   private List<Bike> bikes;
   /** name of the station */
   private String name;
   private int capacity;
   /**
     * Constructeur de la classe Station.
     *
     * @param name     Nom de la station.
     * @param capacity Capacité maximale de la station.
   */
   public Station(String name, int capacity) {
      this.name = name;
      this.capacity = capacity;
      this.bikes = new ArrayList<>(capacity);
      bikes = new ArrayList<>(capacity);
        // Initialize all slots with null to represent empty slots.
        for (int i = 0; i < capacity; i++) {
            bikes.add(null);
        }
   }

   /**
    * Renvoie le nom de la station
    @return String
    */
   public String getName() {
      return this.name;
   }

   /**
     * Renvoie la capacité maximale de la station.
     *
     * @return La capacité maximale de la station.
     */
   public int getCapacity() {
      return this.capacity;
   }

   /**
     * Renvoie le nombre de vélos actuellement disponibles (présents) dans la station.
     *
     * @return Le nombre de vélos disponibles.
     */
   public int getNumberOfBikes() {
      int count = 0;
      for (Bike bike : bikes) {
         if (bike != null) count++;
      }
      return count;
   }
   /**
     * Retire un vélo de la station.
     *
     * @param bike Le vélo à retirer.
     * @throws BikeNotRemovableException Exception indiquant qu'il est impossible de retirer le vélo.
   */

   public void TakeBike(Bike bike) throws BikeNotRemovableException {
      if (bikes.contains(bike)) {
          bikes.remove(bike);
      } else {
          // Only throw the exception if the bike was not found in the list
          throw new BikeNotRemovableException("Impossible de retirer le vélo");
      }
  }
   /**
     * Vérifie si la station est pleine (tous les emplacements sont occupés).
     *
     * @return true si la station est pleine, false sinon.
   */
   
   public Boolean IsFull(){
      return (this.getNumberOfBikes()) == (this.capacity);
   } 
   /**
     * Ajoute un vélo à la station à l'emplacement spécifié.
     *
     * @param bike  Le vélo à ajouter.
     * @param space L'emplacement où ajouter le vélo.
     * @throws OccupiedLocationException Exception indiquant que l'emplacement est déjà occupé par un autre vélo.
     */
    
   public void addBike(Bike bike, int space) throws OccupiedLocationException{
      try {
         this.bikes.add(space, bike);     
      
      } catch (IndexOutOfBoundsException e) {
         throw new OccupiedLocationException("la place est déja occupé par un autre vélo");
      }
   } 

   /**
     * Finds the first empty slot in the station.
     *
     * @return the index of the first empty slot, or -1 if the station is full.
     */
   public int findEmptySlot() {
      for (int i = 0; i < bikes.size(); i++) {
          if (bikes.get(i) == null) {
              return i;
          }
      }
      return -1; // No empty slots available
   }
   /** 
   * @return Le vélo à retirer, ou null s'il n'y a aucun vélo disponible.
   * */
   public Bike selectBikeForRemoval() {
      // Example: select the first bike found in the station for removal
      for (Bike bike : bikes) {
         if (bike != null) {
            return bike;
         }
      }
      return null; // No bikes available for removal
   }

   /**
     * Gets the list of bikes in the station.
     * 
     * @return The list of bikes.
     */
    public List<Bike> getBikes() {
      return bikes;
   }
   



}

