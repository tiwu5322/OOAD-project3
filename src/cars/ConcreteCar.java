package cars;

public class ConcreteCar extends Car {


    public ConcreteCar(String license, String category) {
        super(license, category);
    }

    public ConcreteCar(String license, String category, String description, float rental_rate, int rental_duration, int start_date, int end_date, double total_cost){
        super(license, category, description, rental_rate, rental_duration, start_date, end_date, total_cost);
    }

    public String get_description(){
        return this.description;
    }


    public double get_cost() {
        return get_total_cost();
    }

}
