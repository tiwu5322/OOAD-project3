package store;

import cars.*;
import customer.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CarStore extends Observable{
    private RentalBehavior casual = new CasualRentBehavior();
    private RentalBehavior business = new BusinessRentBehavior();
    private RentalBehavior regular = new RegularRentBehavior();



    private List<Customer> list_of_customers = new ArrayList<>();
    private List<Customer> list_of_completed = new ArrayList<>();

    private int Total_number_of_rented_car = 0;
    private double Total_income_per_type = 0.0;
    private double Total_income = 0.0;
    private Customer current_customer;
    private Car current_car;
    private Tuple<Integer, Integer> request;


    private List<Car> inventory = new ArrayList<>();

    private int date = 1;
    private int number_of_today_customers = 0;


    public void newCustomer(String name, RentalBehavior a, List<Customer> list_of_customers, RentalBehavior casual, RentalBehavior business, RentalBehavior regular) {

        Customer current_customer;
        if (a == casual) {
            current_customer = new CasualCustomer(name, a);
            list_of_customers.add(current_customer);
        } else if (a == business) {
            current_customer = new BusinessCustomer(name, a);
            list_of_customers.add(current_customer);
        } else if (a == regular) {
            current_customer = new RegularCustomer(name, a);
            list_of_customers.add(current_customer);
        }


    }

    public int select_a_customer(List<Customer> list_of_customers) {
        int i = 12;
        Random randomizer = new Random();
        int rand = randomizer.nextInt(list_of_customers.size());
        Customer random_customer = list_of_customers.get(rand);
        while (!random_customer.selectable()) {
            rand = randomizer.nextInt(list_of_customers.size());
            random_customer = list_of_customers.get(rand);
            i -= 1;
            if (i == 0) {
                return -1;
            }
        }

        return rand;

    }

    public Car addsOn(Car car) {
        Random rand = new Random();
        int childseat_num = rand.nextInt(5);
        for (int i=0; i<childseat_num; i++){
            car = new ChildSeat(car);
        }
        int GPS_num = rand.nextInt(2);
        for (int i=0; i<GPS_num; i++){
            car = new GPS(car);
        }
        int radio_num = rand.nextInt(2);
        for (int i=0; i<radio_num; i++){
            car = new Radio(car);
        }
        return car;
    }
    public int calculate_the_number_of_cars(List<Customer> list){
        Total_number_of_rented_car = 0;
        for (int i = 0; i < list.size(); i++){
            for (int j = 0; j < list.get(i).getList_of_cars().size(); j++){
                Total_number_of_rented_car += 1;
            }
        }
        return Total_number_of_rented_car;
    }
    public double calculate_the_total_income_per_type(List<Customer> list, String type){
        Total_income_per_type = 0.0;
        for (int i = 0; i < list.size(); i++){
            if (list.get(i).getType().equals(type)){
                Total_income_per_type += list.get(i).getTotalCost();

            }

        }
        return Total_income_per_type;
    }
    public void summary(){
        System.out.println("--------------------------------------");
        Total_number_of_rented_car = calculate_the_number_of_cars(list_of_completed) + calculate_the_number_of_cars(list_of_customers);
        System.out.println("The total number of rented car: " + Total_number_of_rented_car);
        System.out.println("-----------------------------");
        System.out.println("Total income per customer type: ");
        Total_income_per_type = calculate_the_total_income_per_type(list_of_completed, "Casual")
                + calculate_the_total_income_per_type(list_of_customers, "Casual");
        Total_income += Total_income_per_type;
        System.out.println("The total income of Casual type: " + Total_income_per_type);
        System.out.println("-----------------------------");
        Total_income_per_type = calculate_the_total_income_per_type(list_of_completed, "Business")
                + calculate_the_total_income_per_type(list_of_customers, "Business");
        Total_income += Total_income_per_type;
        System.out.println("The total income of Standard type: " + Total_income_per_type);
        System.out.println("-----------------------------");
        Total_income_per_type = calculate_the_total_income_per_type(list_of_completed, "Regular")
                + calculate_the_total_income_per_type(list_of_customers, "Regular");
        Total_income += Total_income_per_type;
        System.out.println("The total income of Luxury type: " + Total_income_per_type);
        System.out.println("-----------------------------");
        System.out.println("--------------------------------------");
        System.out.println("**************************************");
        System.out.println("--------------------------------------");
        System.out.println("The total store income: " + Total_income);
        System.out.println("-----------------------------");


    }
    public void update(List<Customer> list_of_completed, List<Car> inventory, List<Customer> list_of_customers){
        observer.updateCompleted(list_of_completed);
        observer.updateInventory(inventory);
        observer.updateActive(list_of_customers);
    }

    public void create_customers(Random randomizer){
        List<RentalBehavior> list_of_types = new ArrayList<>();
        list_of_types.add(casual);
        list_of_types.add(business);
        list_of_types.add(regular);

        List<String> list_of_name = new ArrayList<>();
        list_of_name.add("Ada");
        list_of_name.add("Amy");
        list_of_name.add("Bob");
        list_of_name.add("Bruce");
        list_of_name.add("Chris");
        list_of_name.add("Coco");
        list_of_name.add("David");
        list_of_name.add("Lilly");
        list_of_name.add("Lisa");
        list_of_name.add("Joe");
        list_of_name.add("James");
        list_of_name.add("Martin");

        //create customer objects
        for (int i = 0; i < list_of_name.size(); i++) {
            if (i < 3) {
                newCustomer(list_of_name.get(i), list_of_types.get(i), list_of_customers, casual, business, regular);
                newCustomer(list_of_name.get(i), list_of_types.get(i), list_of_completed, casual, business, regular);
            } else {
                RentalBehavior random_type = list_of_types.get(randomizer.nextInt(list_of_types.size()));
                newCustomer(list_of_name.get(i), random_type, list_of_customers, casual, business, regular);
                newCustomer(list_of_name.get(i), random_type, list_of_completed, casual, business, regular);
            }
        }
    }

    public void create_cars(){
        // Economy 6 total
        Car econ_car1 = ConcreteRentalFactory.create_car_rental("ECON001", "Economy");
        Car econ_car2 = ConcreteRentalFactory.create_car_rental("ECON002", "Economy");
        Car econ_car3 = ConcreteRentalFactory.create_car_rental("ECON003", "Economy");
        Car econ_car4 = ConcreteRentalFactory.create_car_rental("ECON004", "Economy");
        Car econ_car5 = ConcreteRentalFactory.create_car_rental("ECON005", "Economy");
        Car econ_car6 = ConcreteRentalFactory.create_car_rental("ECON006", "Economy");
        inventory.add(econ_car1); inventory.add(econ_car2); inventory.add(econ_car3);
        inventory.add(econ_car4); inventory.add(econ_car5); inventory.add(econ_car6);

        // Standard 4 total
        Car stad_car1 = ConcreteRentalFactory.create_car_rental("STAD001", "Standard");
        Car stad_car2 = ConcreteRentalFactory.create_car_rental("STAD002", "Standard");
        Car stad_car3 = ConcreteRentalFactory.create_car_rental("STAD003", "Standard");
        Car stad_car4 = ConcreteRentalFactory.create_car_rental("STAD004", "Standard");
        inventory.add(stad_car1); inventory.add(stad_car2); inventory.add(stad_car3); inventory.add(stad_car4);

        // Luxury 4 total
        Car luxu_car1 = ConcreteRentalFactory.create_car_rental("LUXU001", "Luxury");
        Car luxu_car2 = ConcreteRentalFactory.create_car_rental("LUXU002", "Luxury");
        Car luxu_car3 = ConcreteRentalFactory.create_car_rental("LUXU003", "Luxury");
        Car luxu_car4 = ConcreteRentalFactory.create_car_rental("LUXU004", "Luxury");
        inventory.add(luxu_car1); inventory.add(luxu_car2); inventory.add(luxu_car3); inventory.add(luxu_car4);

        // Minivan 5 total
        Car minivan1 = ConcreteRentalFactory.create_car_rental("MVAN001", "Minivan");
        Car minivan2 = ConcreteRentalFactory.create_car_rental("MVAN002", "Minivan");
        Car minivan3 = ConcreteRentalFactory.create_car_rental("MVAN003", "Minivan");
        Car minivan4 = ConcreteRentalFactory.create_car_rental("MVAN004", "Minivan");
        Car minivan5 = ConcreteRentalFactory.create_car_rental("MVAN005", "Minivan");
        inventory.add(minivan1); inventory.add(minivan2); inventory.add(minivan3);
        inventory.add(minivan4); inventory.add(minivan5);

        // SUV 5 total
        Car suv1 = ConcreteRentalFactory.create_car_rental("SUV001", "SUV");
        Car suv2 = ConcreteRentalFactory.create_car_rental("SUV002", "SUV");
        Car suv3 = ConcreteRentalFactory.create_car_rental("SUV003", "SUV");
        Car suv4 = ConcreteRentalFactory.create_car_rental("SUV004", "SUV");
        Car suv5 = ConcreteRentalFactory.create_car_rental("SUV005", "SUV");
        inventory.add(suv1); inventory.add(suv2); inventory.add(suv3); inventory.add(suv4); inventory.add(suv5);
    }

    public void run() {
        Random randomizer = new Random();

        //create customer objects
        create_customers(randomizer);

        //create cars
        create_cars();

        //Our shop is open now
        while (date < 7) {
            System.out.println("-----------------------------------------------------");
            System.out.println("-----------------------------------------------------");
            System.out.println("Today is date: " + date);
            System.out.println("-----------------------------------------------------");

            //customer return
            //add completed list
            //adjust inventory
            for (int i = 0; i < list_of_customers.size(); i++) {

                if (list_of_customers.get(i).getRental_number() != 0) {
                    int list_size = list_of_customers.get(i).getList_of_cars().size();
                    for (int j = list_size - 1; j >= 0; j--) {
                        //if car returned
                        if (list_of_customers.get(i).getList_of_cars().get(j).get_end_date() < OpenClass.today) {
                            //get will be removed the object

                            Car temp_c= list_of_customers.get(i).getList_of_cars().get(j);
                            Car temp_completed = new ConcreteCar(temp_c.get_license(), temp_c.get_category(), temp_c.get_description(), temp_c.get_rental_rate(), temp_c.get_rental_duration(), temp_c.get_start_date(), temp_c.get_end_date(), temp_c.get_cost());

                            //adjust inventory
                            Car new_car = ConcreteRentalFactory.create_car_rental(temp_c.get_license(), temp_c.get_category());
                            inventory.add(new_car);

                            //adjust completed list
                            list_of_completed.get(i).getList_of_cars().add(temp_completed);

                            //remove car from list_of_cars of customer
                            list_of_customers.get(i).remove_ith_of_car_list(j);
                            list_of_customers.get(i).setRental_number(list_of_customers.get(i).getRental_number() - 1);
                        }
                    }
                }
            }

            //# of customer
            number_of_today_customers = randomizer.nextInt(12) + 1;
            if (number_of_today_customers > inventory.size() - 2) {
                number_of_today_customers = inventory.size() - 2;
            }

            while (number_of_today_customers > 0) {

                //get a random qualified customer
                int rand_idx = select_a_customer(list_of_customers);
                if (rand_idx == -1) {
                    break;
                }
                current_customer = list_of_customers.get(rand_idx);

                //the customer rent a car.
                request = current_customer.rent(0, 0);

                //assign a car/cars
                for (int i = 0; i < request.x; i++){
                    //get a random qualified car
                    current_car = inventory.get(randomizer.nextInt(inventory.size()));

                    current_car.set_rental_duration(request.y);
                    current_car.set_start_date(OpenClass.today);
                    current_car.set_end_date(OpenClass.today + request.y - 1);

                    current_car = addsOn(current_car);

                    current_customer.getList_of_cars().add(current_car);

                    //adjust inventory
                    for (int k = 0; k < inventory.size(); k++){
                        if (inventory.get(k).get_license().equals(current_car.get_license())){
                            inventory.remove(k);
                            break;
                        }
                    }
                }
                number_of_today_customers -= request.x;
            }
            //notify observer
            update(list_of_completed, inventory, list_of_customers);

            date += 1;
            OpenClass.setToday(date);

        }
        summary();



    }

}


