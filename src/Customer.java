import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Customer {

    private String customerName;
    private HashMap<FitnessClass, Boolean> classes;

    Customer(String name) {
        this.customerName = name;
        classes = new HashMap<>();
    }

    public String get_name() {
        return customerName;
    }

    public void add_to_class(FitnessClass fitnessClass) {
        classes.put(fitnessClass, false);
    }

    public void attend_class(FitnessClass fitnessClass) {
        classes.put(fitnessClass, true);
    }

    public void remove_from_class(FitnessClass fitnessClass) {
        classes.remove(fitnessClass);
    }

    public ArrayList<FitnessClass> get_classList(Boolean attended) {
        ArrayList<FitnessClass> classList = new ArrayList<>();
        for (Map.Entry<FitnessClass, Boolean> entry : classes.entrySet()) {
            if (entry.getValue() == attended)
                classList.add(entry.getKey());
        }
        return classList;
    }

    public String toString() {
        return customerName;
    }
}
