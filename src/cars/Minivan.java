package cars;

public class Minivan extends ConcreteCar {

    public Minivan(String license, String category) {
        super(license, category);
    }


    @Override
    public String get_description(){
        description = category + " car; License number: "+ this.get_license() + " -->";
        return description;
    }
    @Override
    public double get_cost() {
        return 50.0 * rental_duration;
    }
}
