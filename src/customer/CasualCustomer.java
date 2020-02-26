package customer;

public class CasualCustomer extends Customer{
    public CasualCustomer(String name, RentalBehavior rentalBehavior){
        super(name, rentalBehavior);
        super.setType("Casual");
    }


}
