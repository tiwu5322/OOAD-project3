package cars;

public class EconomyCar extends ConcreteCar {

    public EconomyCar(String license, String category) {
        super(license, category);
    }

    @Override
    public double get_cost() {
        return 20.0 * rental_duration;
    }

    @Override
    public String get_description(){
        description = category + " car; License number: "+ this.get_license() + " -->";
        return description;
    }
}