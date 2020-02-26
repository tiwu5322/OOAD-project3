package customer;

public class BusinessCustomer extends Customer {
    public BusinessCustomer(String name, RentalBehavior rentalBehavior){
        super(name, rentalBehavior);
        super.setType("Business");
    }



}
