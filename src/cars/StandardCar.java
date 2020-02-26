package cars;

public class StandardCar extends ConcreteCar {

    public StandardCar(String license, String category) {
        super(license, category);
    }



    @Override
    public String get_description(){
        description = category + " car; License number: "+ this.get_license() + " -->";
        return description;
    }
    @Override
    public double get_cost() {
        return 30.0 * rental_duration;
    }
}
