package customer;

import cars.Car;
import cars.CarDecorator;
import store.OpenClass;

import java.util.ArrayList;
import java.util.List;

public abstract class Customer {
    private String name;
    private int rental_number = 0;
    private int return_date = 0;
    private String type;
    private List<Car> list_of_cars = new ArrayList<>();
    private float rental_fee;
    private int seletable = 0;
    private double total_cost = 0.0;
    public RentalBehavior rentalBehavior;



    public Customer(String name, RentalBehavior rentalBehavior) {
        this.name = name;
        this.rentalBehavior = rentalBehavior;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {

        this.type = type;
    }

    public int getRental_number() {
        return this.rental_number;
    }

    public String getName() {


        return this.name;
    }


    public List<Car> getList_of_cars() {

        return this.list_of_cars;
    }


    public void remove_ith_of_car_list(int i) {
        this.list_of_cars.remove(i);
    }

    public void setRental_number(int rental_number) {
        this.rental_number = rental_number;
    }

    public int getReturn_date() {
        return return_date;
    }


    public Tuple<Integer, Integer> rent(int rental_number, int duration){

        Tuple<Integer, Integer> request = rentalBehavior.rent(rental_number, duration);
        request = rentable(request);

//        System.out.println("customer.Customer: adjust number of rental cars: " + request.x);
//        System.out.println("customer.Customer: adjust number of days: " + request.y);
        this.return_date = request.y + OpenClass.today - 1;
        this.rental_number += request.x;

        return request;
    }



    public Tuple<Integer, Integer> rentable(Tuple<Integer, Integer> request){
        int total = request.x + this.rental_number;

        if (total > 3){
            request.x = 3 - this.rental_number;
        }

        if (this.return_date >= OpenClass.today){
            int required_duration = this.return_date - OpenClass.today + 1;
            if (request.y != required_duration){
                request.y = required_duration;
            }

        }

        return request;
    }
    public boolean selectable(){

        if(this.seletable == OpenClass.today && this.type.equals("Casual")){
            return false;
        }
        if (this.type.equals("Regular")) {
            if (this.return_date >= OpenClass.today) {
                if ((this.return_date - OpenClass.today + 1) < 3) {
                    return false;
                }
            }

        }else if(this.type.equals("Business")){
            if (this.return_date >= OpenClass.today){
                if((this.return_date - OpenClass.today + 1) < 7){
                    return false;
                }
            }

        }

        if (this.rental_number == 3 && this.return_date >= OpenClass.today){
            return false;
        }
        this.seletable = OpenClass.today;
        return true;

    }
    public double getTotalCost(){
        this.total_cost = pay_bill();
        return this.total_cost;
    }

    public double pay_bill(){
        double t_cost = 0.0;
        for (int i = 0; i < this.list_of_cars.size(); i++){
            t_cost += list_of_cars.get(i).get_cost();
        }
        return t_cost;
    }

}
