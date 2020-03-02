package store;
import customer.*;
import test.JunitTest;

public class Main {
    public static void main(String[] args){
//        CarStore store = new CarStore();
//        store.run();
        JunitTest test = new JunitTest();
        test.testBusinessBehavior();
        test.testCasualBehavior();
        test.testRegularBehavior();
        test.returnOnSameDay();
        test.noMoreThan3Cars();
        test.testEconomyCarCostOfRentOneDay();
        test.testSUVCarCostOfRentTwoDay();
        test.testStandardCarCostOfRentThreeDay();
        test.testMinivanCarCostOfRentFourDay();
        test.testLuxuryCarCostOfRentFiveDay();



    }
}
