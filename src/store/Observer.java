package store;

import cars.Car;
import customer.Customer;

import java.util.List;

interface Observer {

    void updateInventory(List<Car> inventory);
    void displayInventory();
    void updateActive(List<Customer> list);
    void displayActive();
    void updateCompleted(List<Customer> list);
    void displayCompleted();



}

