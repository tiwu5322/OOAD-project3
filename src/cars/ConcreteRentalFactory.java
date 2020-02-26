package cars;

public class ConcreteRentalFactory extends RentalFactory {

    public static Car create_car_rental(String license, String category){
        Car car = null;
        switch (category) {
            case "Economy":
                car = new EconomyCar(license, category);
                break;
            case "Standard":
                car = new StandardCar(license, category);
                break;
            case "Luxury":
                car = new LuxuryCar(license, category);
                break;
            case "Minivan":
                car = new Minivan(license, category);
                break;
            case "SUV":
                car = new SUV(license, category);
                break;
            default:
                System.out.println("Error: invalid car category");
                return null;
        }
        return car;
    }
}
