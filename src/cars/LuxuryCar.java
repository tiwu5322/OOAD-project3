package cars;

public class LuxuryCar extends ConcreteCar {

    public LuxuryCar(String license, String category) {
        super(license, category);
    }



    @Override
    public String get_description(){
        description = category + " car; License number: "+ this.get_license() + " -->";
        return description;
    }
    @Override
    public double get_cost() {
        return 60.0 * rental_duration;
    }
}
