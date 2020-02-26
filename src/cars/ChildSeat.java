package cars;

public class ChildSeat extends CarDecorator {
    Car car;
    double rental_rate = 5.0;

    public ChildSeat(Car car) {

        super(car);
        this.car = car;
    }

    public String get_description() {
        return car.get_description() + " Child seat,";
    }

    public double get_cost() {
        return 5.0 + car.get_cost();
    }
}