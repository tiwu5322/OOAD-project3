package cars;

public abstract class CarDecorator extends Car {


    public CarDecorator(Car car){
        super(car.get_license(), car.get_category(), car.get_description(), car.get_rental_rate(), car.get_rental_duration(), car.get_start_date(), car.get_end_date(), car.get_cost());

    }

    public abstract String get_description();

    public abstract double get_cost();




}