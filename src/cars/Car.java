package cars;

public abstract class Car {
    private String license_plate;
    protected String category;
    protected int rental_duration;
    public float rental_rate;
    public String description;
    protected int start_date;
    protected int end_date;
    protected double total_cost;

    public Car(String license, String category) {
        this.license_plate = license;
        this.category = category;
    }

    public Car(String license_plate, String category, String description, float rental_rate, int rental_duration, int start_date, int end_date, double total_cost) {

        this.license_plate = license_plate;
        this.category = category;
        this.description = description;
        this.rental_rate = rental_rate;
        this.rental_duration = rental_duration;
        this.start_date = start_date;
        this.end_date = end_date;
        this.total_cost = total_cost;
    }
    public double get_total_cost(){
        return this.total_cost;
    }
    public String get_description(){
        return description;
    }

    public String get_license(){
        return license_plate;
    }

    public String get_category(){
        return category;
    }


    public float get_rental_rate() {
        return rental_rate;
    }
    public void set_start_date(int start_date) {
        this.start_date = start_date;
    }

    public int get_start_date() {
        return start_date;
    }
    public void set_end_date(int end_date) {
        this.end_date = end_date;
    }

    public int get_end_date() {
        return end_date;
    }

    public int get_rental_duration() {
        return rental_duration;
    }
    public void set_rental_duration(int rental_duration) {

        this.rental_duration = rental_duration;
    }

    public abstract double get_cost();


}

