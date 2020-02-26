package store;


import cars.Car;
import customer.Customer;

import java.util.List;

public abstract class Observable {
    protected ConcreteObserver observer = new ConcreteObserver();

    public void update(List<Customer> list_of_completed, List<Car> inventory, List<Customer> list_of_customers){
        observer.updateCompleted(list_of_completed);
        observer.updateInventory(inventory);
        observer.updateActive(list_of_customers);
    }

}
