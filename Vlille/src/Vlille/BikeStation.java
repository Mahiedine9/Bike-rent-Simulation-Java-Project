package vlille.station;

import vlille.bike.Bike;

public class BikeStation {

   /** array of slots for bikes in the station */
   private Bike[] bikes;
   /** name of the station */
   private String name;
   private int emptyTimeIntervals;
   private int fullTimeIntervals;

   public BikeStation(String name, int capacity) {
      this.name = name;
      this.bikes = new Bike[capacity];
      this.emptyTimeIntervals = 0;
      this.fullTimeIntervals = 0;
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
      return this.bikes.length;
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
    * Renvoie le plus petit indice correspondant à un emplacement libre pour un vélo.
    */
   public int firstFreeSlot() {
      for (int i = 0; i < bikes.length; i++) {
         if (bikes[i] == null) return i;
      }
      return -1;
   }

   /**
    * Permet de déposer un vélo dans une station.
    */
   public boolean dropBike(Bike bike) {
      int freeSlot = firstFreeSlot();
      if (freeSlot != -1) {
         bikes[freeSlot] = bike;
         return true;
      }
      return false;
   }

   /**
    * Permet de prendre un vélo dans une station.
    */
   public Bike takeBike(int i) throws BikeNotAvailableException {
      if (i < 0 || i >= bikes.length || bikes[i] == null) {
         throw new BikeNotAvailableException("Pas de vélo disponible à l'emplacement " + i);
      }
      Bike takenBike = bikes[i];
      bikes[i] = null;
      return takenBike;
   }

}

