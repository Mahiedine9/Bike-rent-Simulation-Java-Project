



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
        if (bikes.size() < capacity){
            bikes.add(bike);
            return true;

        }
        return false;
    }

    public Bike TakeBike(){
        if (bikes.size() > 0){
            return bikes.remove(0);

        }
        return null;
    }
}