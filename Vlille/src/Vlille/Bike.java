package vlille.bike;

/**
 * A class to model bike that are rent in a bike station.
 *
 */
public class Bike {

   /** bike's rental price */   
   public static final float RENTAL_PRICE = 100;
   /** bike's default color */
   public static final String DEFAULT_COLOR = "red";
   /** Max number of rentals before service is required */
   private static final int MAX_RENTALS_BEFORE_SERVICE = 50;

   private String id;
   private BikeModel model;
   private String color;
   private boolean hasBasket;
   private boolean hasLuggageRack;
   private int rentalCount;
   private boolean isServiceRequired;

   /**
    * Build a bike with given id and model, and additional features.
    * @param id this bike's id
    * @param model this bike's model
    * @param isElectric indicates if the bike is electric
    * @param hasBasket indicates if the bike has a basket
    * @param hasLuggageRack indicates if the bike has a luggage rack
    */
    public Bike(String id, BikeModel model, boolean hasBasket, boolean hasLuggageRack) {
      this.id = id;
      this.model = model;
      this.color = DEFAULT_COLOR;
      this.hasBasket = hasBasket;
      this.hasLuggageRack = hasLuggageRack;
      this.rentalCount = 0;
      this.isServiceRequired = false;
   }

   public void rentBike() {
      rentalCount++;
      if (rentalCount >= MAX_RENTALS_BEFORE_SERVICE) {
         isServiceRequired = true;
      }
   }

   public boolean isAvailableForRent() {
      return !isServiceRequired;
   }

   public boolean hasBasket() {
      return hasBasket;
   }

   public void setBasket(boolean hasBasket) {
      this.hasBasket = hasBasket;
   }

   public boolean hasLuggageRack() {
      return hasLuggageRack;
   }

   public void setLuggageRack(boolean hasLuggageRack) {
      this.hasLuggageRack = hasLuggageRack;
   }

   public boolean isServiceRequired() {
      return isServiceRequired;
   }

   public void setServiceRequired(boolean isServiceRequired) {
      this.isServiceRequired = isServiceRequired;
   }

   /**
    * @return the reference of this bike
    */
   public String getId() {
      return this.id;
   }

   /**
    * @return the model of this bike
    */
   public BikeModel getModel() {
      return this.model;
   }

   /**
    * @return the color of this bike
    */
   public String getColor() {
      return this.color;
   }

   /** two bikes are equals if they have same id
    * @param o the object to compare this bike with
    */
   public boolean equals(Object o) {
      if (o instanceof Bike) {
	  Bike other = (Bike) o;	  
         return this.id.equals(other.id);
      } else {
         return false;
      }
   }

   public String toString() {
      return "Bike{" +
             "id='" + id + '\'' +
             ", model=" + model +
             ", color='" + color + '\'' +
             ", hasBasket=" + hasBasket +
             ", hasLuggageRack=" + hasLuggageRack +
             ", rentalCount=" + rentalCount +
             ", isServiceRequired=" + isServiceRequired +
             '}';
  }
}
