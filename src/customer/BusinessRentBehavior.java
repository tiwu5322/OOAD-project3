package customer;

public class BusinessRentBehavior implements RentalBehavior {

    public Tuple<Integer, Integer> rent(int n_cars, int duration){

        n_cars = 3;
        duration = 7;
        Tuple<Integer, Integer> request = new Tuple<>(n_cars, duration);
        return request;

    }
}
