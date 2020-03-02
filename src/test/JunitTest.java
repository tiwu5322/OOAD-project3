package test;
import customer.*;
import cars.*;
import store.*;

import org.junit.Test;
import static org.junit.Assert.*;


public class JunitTest {
    private RentalBehavior casual = new CasualRentBehavior();
    private RentalBehavior business = new BusinessRentBehavior();
    private RentalBehavior regular = new RegularRentBehavior();
    private Tuple<Integer, Integer> request;

    @Test
    public void testBusinessBehavior(){
        System.out.println("JUnit test 1: ");
        System.out.println("... Testing behavior of the Business customer ... ");

        Customer customer = new BusinessCustomer("lily", business);
        request = customer.rent(0,0);

        try {
            int result = request.x;
            assertEquals(3, result);
            result = request.y;
            assertEquals(7, result);
        } catch (AssertionError e) {
            System.out.println("Oops!!! Test failed.");
            throw e;
        }
        System.out.println("Congratulations!!! Test passed.\n");

    }

    @Test
    public void testCasualBehavior(){
        System.out.println("JUnit test 2: ");
        System.out.println("... Testing behavior of the Casual customer ... ");

        Customer customer = new BusinessCustomer("lily", casual);
        request = customer.rent(0,0);

        try {
            int result = request.x;
            assertEquals(1, result);
            result = request.y;
            assertTrue(result > 0 && result < 4 );
        } catch (AssertionError e) {
            System.out.println("Oops!!! Test failed.");
            throw e;
        }
        System.out.println("Congratulations!!! Test passed.\n");

    }

    @Test
    public void testRegularBehavior(){
        System.out.println("JUnit test 3: ");
        System.out.println("... Testing behavior of the Regular customer ... ");

        Customer customer = new BusinessCustomer("lily", regular);
        request = customer.rent(0,0);

        try {
            int result = request.x;
            assertTrue(result > 0 && result < 4);
            result = request.y;
            assertTrue(result > 2 && result < 6 );
        } catch (AssertionError e) {
            System.out.println("Oops!!! Test failed.");
            throw e;
        }
        System.out.println("Congratulations!!! Test passed.\n");

    }
    @Test
    public void returnOnSameDay(){
        System.out.println("JUnit test 4: ");
        System.out.println("... Testing return on the same day ... ");

        Customer customer = new BusinessCustomer("lily", regular);
        //rent first car
        request = customer.rent(0,0);
        //rent second car
        Tuple<Integer, Integer> request1 = customer.rent(0,0);

        try {
            assertEquals(request.y, request1.y);

        } catch (AssertionError e) {
            System.out.println("Oops!!! Test failed.");
            throw e;
        }
        System.out.println("Congratulations!!! Test passed.\n");

    }
    @Test
    public void noMoreThan3Cars(){
        System.out.println("JUnit test 5: ");
        System.out.println("... Testing no more than 3 cars per customer in total ... ");

        CarStore store = new CarStore();

        Customer customer = new BusinessCustomer("lily", regular);
        //rent first car
        request = customer.rent(0,0);
        //rent second car
        Tuple<Integer, Integer> request1 = customer.rent(0,0);

        try {
            assertTrue(request.x + request1.x < 4);

        } catch (AssertionError e) {
            System.out.println("Oops!!! Test failed.");
            throw e;
        }
        System.out.println("Congratulations!!! Test passed.\n");

    }

    public int countString(String findStr, String str){
        int lastIndex = 0;
        int count = 0;
        while(lastIndex != -1){

            lastIndex = str.indexOf(findStr,lastIndex);

            if(lastIndex != -1){
                count ++;
                lastIndex += findStr.length();
            }
        }
        return count;

    }
    public double calcuteAddOn(int seat_count, int gps_count, int radio_count){
        double radio = 15.0 * radio_count;
        double GPS = 10.0 * gps_count;
        double seat = 5.0 * seat_count;
        return radio + GPS + seat;
    }

    @Test
    public void testEconomyCarCostOfRentOneDay(){
        System.out.println("JUnit test 6: ");
        System.out.println("... Testing Economy Car Cost of one day with randomly adds on... ");

        CarStore store = new CarStore();

        Car economyCar = ConcreteRentalFactory.create_car_rental("ECON001", "Economy");
        economyCar.set_rental_duration(1);
        economyCar.set_start_date(1);
        economyCar.set_end_date(1);

        economyCar = store.addsOn(economyCar);
        String description = economyCar.get_description();
        int seat_count = countString("seat", description);
        int gps_count = countString("GPS", description);
        int radio_count = countString("radio", description);


        try {
            assertEquals(calcuteAddOn(seat_count, gps_count, radio_count) + 20.0 * economyCar.get_rental_duration(), economyCar.get_cost());
            assertEquals(economyCar.get_category(), "Economy");
            assertEquals(economyCar.get_license(), "ECON001");

        } catch (AssertionError e) {
            System.out.println("Oops!!! Test failed.");
            throw e;
        }
        System.out.println("Congratulations!!! Test passed.\n");


    }

    @Test
    public void testSUVCarCostOfRentTwoDay(){
        System.out.println("JUnit test 7: ");
        System.out.println("... Testing SUV Car Cost of two days with randomly adds on ... ");

        CarStore store = new CarStore();

        Car SUVCar = ConcreteRentalFactory.create_car_rental("SUV001", "SUV");
        SUVCar.set_rental_duration(2);
        SUVCar.set_start_date(1);
        SUVCar.set_end_date(2);

        SUVCar = store.addsOn(SUVCar);
        String description = SUVCar.get_description();
        int seat_count = countString("seat", description);
        int gps_count = countString("GPS", description);
        int radio_count = countString("radio", description);


        try {
            assertEquals(calcuteAddOn(seat_count, gps_count, radio_count) + 40.0 * SUVCar.get_rental_duration(), SUVCar.get_cost());
            assertEquals(SUVCar.get_category(), "SUV");
            assertEquals(SUVCar.get_license(), "SUV001");

        } catch (AssertionError e) {
            System.out.println("Oops!!! Test failed.");
            throw e;
        }
        System.out.println("Congratulations!!! Test passed.\n");


    }
    @Test
    public void testStandardCarCostOfRentThreeDay(){
        System.out.println("JUnit test 8: ");
        System.out.println("... Testing Standard Car Cost of three days with randomly adds on ... ");

        CarStore store = new CarStore();

        Car StandardCar = ConcreteRentalFactory.create_car_rental("STAD001", "Standard");
        StandardCar.set_rental_duration(3);
        StandardCar.set_start_date(1);
        StandardCar.set_end_date(3);

        StandardCar = store.addsOn(StandardCar);
        String description = StandardCar.get_description();
        int seat_count = countString("seat", description);
        int gps_count = countString("GPS", description);
        int radio_count = countString("radio", description);


        try {
            assertEquals(calcuteAddOn(seat_count, gps_count, radio_count) + 30.0 * StandardCar.get_rental_duration(), StandardCar.get_cost());
            assertEquals(StandardCar.get_category(), "Standard");
            assertEquals(StandardCar.get_license(), "STAD001");

        } catch (AssertionError e) {
            System.out.println("Oops!!! Test failed.");
            throw e;
        }
        System.out.println("Congratulations!!! Test passed.\n");

    }

    @Test
    public void testMinivanCarCostOfRentFourDay(){
        System.out.println("JUnit test 9: ");
        System.out.println("... Testing Minivan Car Cost of four days with randomly adds on ... ");

        CarStore store = new CarStore();

        Car MinivanCar = ConcreteRentalFactory.create_car_rental("MVAN001", "Minivan");
        MinivanCar.set_rental_duration(4);
        MinivanCar.set_start_date(1);
        MinivanCar.set_end_date(4);

        MinivanCar = store.addsOn(MinivanCar);
        String description = MinivanCar.get_description();
        int seat_count = countString("seat", description);
        int gps_count = countString("GPS", description);
        int radio_count = countString("radio", description);


        try {
            assertEquals(calcuteAddOn(seat_count, gps_count, radio_count) + 50.0 * MinivanCar.get_rental_duration(), MinivanCar.get_cost());
            assertEquals(MinivanCar.get_category(), "Minivan");
            assertEquals(MinivanCar.get_license(), "MVAN001");

        } catch (AssertionError e) {
            System.out.println("Oops!!! Test failed.");
            throw e;
        }
        System.out.println("Congratulations!!! Test passed.\n");

    }

    @Test
    public void testLuxuryCarCostOfRentFiveDay(){
        System.out.println("JUnit test 10: ");
        System.out.println("... Testing Minivan Car Cost of five days with randomly adds on ... ");

        CarStore store = new CarStore();

        Car LuxuryCar = ConcreteRentalFactory.create_car_rental("LUXU001", "Luxury");
        LuxuryCar.set_rental_duration(5);
        LuxuryCar.set_start_date(1);
        LuxuryCar.set_end_date(5);

        LuxuryCar = store.addsOn(LuxuryCar);
        String description = LuxuryCar.get_description();
        int seat_count = countString("seat", description);
        int gps_count = countString("GPS", description);
        int radio_count = countString("radio", description);


        try {
            assertEquals(calcuteAddOn(seat_count, gps_count, radio_count) + 60.0 * LuxuryCar.get_rental_duration(), LuxuryCar.get_cost());
            assertEquals(LuxuryCar.get_category(), "Luxury");
            assertEquals(LuxuryCar.get_license(), "LUXU001");

        } catch (AssertionError e) {
            System.out.println("Oops!!! Test failed.");
            throw e;
        }
        System.out.println("Congratulations!!! Test passed.\n");

    }



}
