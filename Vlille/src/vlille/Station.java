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

   /**
    * Permet de déposer un vélo dans une station.
    */
   public void TakeBike(Bike bike) throws BikeNotRemovableException{
      if (bikes.contains(bike)){
         bikes.remove(bike);
      }
      throw new BikeNotRemovableException("impossible de retirer le vélo");
   }

   public Boolean IsFull(){
      return (this.getNumberOfBikes()) == (this.capacity);
   } 

    // deposer un velo
   public void AddBike(Bike bike, int space) throws OccupiedLocationException{
      try {
         this.bikes.add(space, bike);     
      
      } catch (IndexOutOfBoundsException e) {
         throw new OccupiedLocationException("la place est déja occupé par un autre vélo");
      }
   } 

}

