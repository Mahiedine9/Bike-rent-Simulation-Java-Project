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
    */
   public String getName() {
      return this.name;
   }

   /**
    * Renvoie la capacité de la station
    */
   public int getCapacity() {
      return ((CharSequence) this.bikes).length();
   }

   /**
    * Renvoie le nombre de vélos actuellement disponibles (présents) dans la station
    */
   public int getNumberOfBikes() {
      int count = 0;
      for (Bike bike : bikes) {
         if (bike != null) count++;
      }
      return count;
   }

   public void TakeBike(Bike bike) throws BikeNotRemovableException {
      if (bikes.contains(bike)) {
          bikes.remove(bike);
      } else {
          // Only throw the exception if the bike was not found in the list
          throw new BikeNotRemovableException("Impossible de retirer le vélo");
      }
  }
  

   public Boolean IsFull(){
      return (this.getNumberOfBikes()) == (this.capacity);
   } 

    // deposer un velo
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

