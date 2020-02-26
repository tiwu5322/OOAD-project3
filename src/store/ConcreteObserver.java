package store;

import cars.Car;
import customer.Customer;

import java.util.ArrayList;
import java.util.List;

public class ConcreteObserver implements Observer{
    private double daily_income = 0.0;
    private double total_income = 0.0;
    private List<Car> inventory = new ArrayList<>();
    private List<Customer> active = new ArrayList<>();
    private List<Customer> completed = new ArrayList<>();

    public void updateInventory(List<Car> inventory){
        this.inventory = inventory;
        displayInventory();
    }
    public void displayInventory(){
        System.out.println("**********************");
        System.out.println("Inventory: " + inventory.size());
        System.out.println("**********************");
        for (int i = 0; i < inventory.size(); i++){
            System.out.println(this.inventory.get(i).get_category() + " Car" + i + " License plate: " + this.inventory.get(i).get_license());
        }

    }

    public void updateActive(List<Customer> list){
        this.active = list;
        displayActive();
    }

    public void displayActive(){
        int count = 0;
        System.out.println("--------------------------------------");
        System.out.println("Active List: ");
        for (int i = 0; i < active.size(); i++){
            if(active.get(i).getList_of_cars().size() > 0){
                System.out.println("Name: " + active.get(i).getName() + " (" + active.get(i).getType() + ")");
                List<Car> car_list = this.active.get(i).getList_of_cars();
                for (int j = 0; j < car_list.size(); j++){
                    System.out.println("License plate: " + car_list.get(j).get_license());
                    count += 1;
                }
                System.out.println("Return date: " + active.get(i).getReturn_date());
                System.out.println("-------");
                daily_income += active.get(i).getTotalCost();
            }
        }
        System.out.println("**********************");
        System.out.println("Active car #: "+count);
        System.out.println("**********************");
    }

    public void updateCompleted(List<Customer> list){
        this.completed = list;
        displayCompleted();
    }
    public void displayCompleted(){
        int count = 0;
        System.out.println("--------------------------------------");
        System.out.println("Completed: " );
        for (int i = 0; i < completed.size(); i++){
            if(completed.get(i).getList_of_cars().size() > 0){
                System.out.println("Customer Name: " + completed.get(i).getName() + " Customer type: " + completed.get(i).getType());
                List<Car> car_list = this.completed.get(i).getList_of_cars();
                for (int j = 0; j < car_list.size(); j++){
                    System.out.println(car_list.get(j).get_description());
//                    System.out.println("License plate: " + car_list.get(j).get_license());
                    System.out.println("Duration: " + car_list.get(j).get_rental_duration());
                    System.out.println("Start: " + car_list.get(j).get_start_date());
                    System.out.println("End: " + car_list.get(j).get_end_date());
                    System.out.println("The cost of the current car: " + car_list.get(j).get_cost());

                    count += 1;
                }
                System.out.println("Total cost of the current customer: " + completed.get(i).getTotalCost());
                System.out.println("-------");
                daily_income += completed.get(i).getTotalCost();
            }
        }
        System.out.println("**********************");
        System.out.println("Returned cars #: " + count);
        System.out.println("**********************");
    }

}
