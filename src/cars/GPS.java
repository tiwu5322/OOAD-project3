package cars;

public class GPS extends CarDecorator {
    Car car;
//    private double rental_rate = 10.0;

    public GPS(Car car) {
        super(car);
        this.car = car;
    }

    public String get_description() {
        return car.get_description() + " GPS";
    }

    public double get_cost() {
        return 10.0 + car.get_cost();
    }
}
