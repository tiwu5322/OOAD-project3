package cars;

public class Radio extends CarDecorator {
    Car car;
    double rental_rate = 15.0;

    public Radio(Car car) {
        super(car);
        this.car = car;
    }

    public String get_description(){
        return car.get_description() + " Satellite radio";
    }

    public double get_cost() {
        return 15.0 + car.get_cost();
    }
}
