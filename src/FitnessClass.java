import java.util.HashMap;

abstract class FitnessClass {

    //Static class prices
    final static int BODYSCULPT_PRICE = 7;
    final static int BOXERCISE_PRICE = 20;
    final static int SPIN_PRICE = 10;
    final static int YOGA_PRICE = 15;
    final static int ZUMBA_PRICE = 8;

    public BookingController.ClassType classType;
    private final int weekNumber;
    private final BookingController.Session session;
    private final HashMap<Customer, Boolean> customerList;

    FitnessClass(int weekNumber, BookingController.Session session) {
        customerList = new HashMap<>();

        this.weekNumber = weekNumber;
        this.session = session;
        this.classType = null;
    }

    //List of booked customer
    HashMap<Customer, Boolean> get_list() {
        return customerList;
    }

    //Add customer to the class
    public void add_customer(Customer customer, Boolean cardPayment) {
        if (customerList.size() < 20)
            this.customerList.put(customer, cardPayment);
    }

    //Remove customer from the class
    void remove_customer(Customer customer) {
        customerList.remove(customer);
    }

    //Mark customer as attended
    //abstract void class_attended(Customer customer);-

    public String toString() {
        String out = getClassName();
        if (customerList.size() == 20)
            out = out + "(Full)";
        return out;
    }

    public int get_weekNumber() {
        return weekNumber;
    }

    public BookingController.Session get_session() {
        return session;
    }

    protected abstract String getClassName();
}
