



public class Station{
    private int id;
    private int capacity;
    private List<Bike> bikes = new ArrayList<>;




    public void Station(int id, int capacity){
        if (capacity <10 || capacity >20){
            throw new IllegalArgumentException("largument doit etre entre 10 et 20");
        }
        this.id = id;
        this.capacity = capacity;
    }

    public boolean DepositBike(Bike bike){
        if (this.bikes.size() < capacity){
            this.bikes.add(bike);
            return true;
        }
        return false;
    }

    public Bike TakeBike(){
        if (this.bikes.size() > 0){
            return this.bikes.remove(0);
        }
        return null;
    }

    public int GetId(){
        return this.id;   
    }


    public boolean LocationIsEmpty(int nb){
        return this.bikes.get(nb) == null;
    }
    



}