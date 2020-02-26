package customer;

import java.util.Random;

public class CasualRentBehavior implements RentalBehavior {

    public Tuple<Integer, Integer> rent(int n_cars, int duration){
        Random random = new Random();

        if (n_cars == 0){
            n_cars = 1;
        }
        if(duration == 0){
            duration = random.nextInt(3) + 1;
        }

        Tuple<Integer, Integer> request = new Tuple<>(n_cars, duration);
        return request;

    }
}
