package customer;

public interface RentalBehavior {

    Tuple<Integer, Integer> rent(int n_cars, int duration);
}
