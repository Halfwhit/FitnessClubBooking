import java.util.ArrayList;

abstract class FitnessClass {

    //Static class prices
    final static int YOGA_PRICE = 15;
    final static int ZUMBA_PRICE = 8;
    final static int SPIN_PRICE = 10;
    final static int BODYSCULPT_PRICE = 7;
    final static int BOXERCISE_PRICE = 20;

    ArrayList<Customer> customerList;

    FitnessClass() {
        customerList = new ArrayList<>();
    }

    //List of booked customer
    ArrayList<Customer> get_list() {
        return customerList;
    }

    //Add customer to the class
    public void add_customer(Customer customer) {
        if (customerList.size() < 20)
            this.customerList.add(customer);
    }

    //Remove customer from the class
    void remove_customer(Customer customer) {
        customerList.remove(customer);
    }

    //Mark customer as attended
    //abstract void class_attended(Customer customer);
    
    public String toString() {
        String out = getClassName();
        if (customerList.size() == 20)
            out = out + " - FULL";
        return out;
    }

    protected abstract String getClassName();
}
