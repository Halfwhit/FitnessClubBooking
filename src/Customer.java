import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

class Customer {

    private final String customerName;
    private final HashMap<FitnessClass, Boolean> classes;

    //Constructor
    Customer(String name) {
        this.customerName = name;
        classes = new HashMap<>();
    }

    //Returns the customer's name
    public String get_name() {
        return customerName;
    }

    //Stores a class to the customers list of classes (Booked or attended)
    public void add_to_class(FitnessClass fitnessClass) {
        classes.put(fitnessClass, false);
    }

    //Update a customers class list to attended
    public void attend_class(FitnessClass fitnessClass) {
        classes.put(fitnessClass, true);
    }

    //Remove class from customers class list (Canceled or reviewed)
    public void remove_from_class(FitnessClass fitnessClass) {
        classes.remove(fitnessClass);
    }

    //Returns booked class list
    public ArrayList<FitnessClass> get_booked() {
        return get_classList(false);
    }

    //Returns attended class list
    public ArrayList<FitnessClass> get_attended() {
        return get_classList(true);
    }

    //Return a sorted ArrayList
    private ArrayList<FitnessClass> get_classList(Boolean attended) {
        ArrayList<FitnessClass> classList = new ArrayList<>();
        for (Map.Entry<FitnessClass, Boolean> entry : classes.entrySet()) {
            if (entry.getValue() == attended)
                classList.add(entry.getKey());
        }

        classList.sort(Comparator.comparingInt(FitnessClass::get_weekNumber));

        return classList;
    }

    //Output customer name
    public String toString() {
        return customerName;
    }

}
