package customer;

public class RegularCustomer extends Customer {
    public RegularCustomer(String name, RentalBehavior rentalBehavior){
        super(name, rentalBehavior);
        super.setType("Regular");
    }

}
