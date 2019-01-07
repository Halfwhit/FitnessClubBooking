import java.util.ArrayList;
import java.util.LinkedHashMap;

public class BookingController {

    //Different class types
    public enum  ClassType {
        BODYSCULPT, BOXERCISE, SPIN, YOGA, ZUMBA
    }

    //Sessions available for classes
    public enum Session {
        SAT_MORN {
            public String toString() {
                return "Saturday Morning  ";
            }
        },
        SAT_AFTER {
            public String toString() {
                return "Saturday Afternoon";
            }
        },
        SAT_EVE_1 {
            public String toString() {
                return "Saturday Evening 1";
            }
        },
        SAT_EVE_2 {
            public String toString() {
                return "Saturday Evening 2";
            }
        },
        SUN_MORN {
            public String toString() {
                return "Sunday Morning    ";
            }
        },
        SUN_AFTER {
            public String toString() {
                return "Sunday Afternoon  ";
            }
        },
        SUN_EVE_1 {
            public String toString() {
                return "Sunday Evening 1  ";
            }
        },
        SUN_EVE_2 {
            public String toString() {
                return "Sunday Evening 2  ";
            }
        }
    }

    private final ArrayList<LinkedHashMap<Session, FitnessClass>> yearlySchedule; //Contains each weeks timetable
    private final ArrayList<Customer> customers; //Contains all registered users

    //Constructor
    public BookingController() {
        yearlySchedule = new ArrayList<>();
        customers = new ArrayList<>();

        populate_yearlySchedule();

        System.err.println("Booking Controller initialised");
    }

    //Adds the default weekly schedule to every week of the year
    private void populate_yearlySchedule() {
        for (int i = 0; i < 52; i++) {
            LinkedHashMap<Session, FitnessClass> schedule;
            int weekNumber = i + 1;
            schedule = new LinkedHashMap<>();

            book_default_classes(schedule, weekNumber);
            yearlySchedule.add(schedule);
        }
    }

    //Adds a class to the BookingController schedule
    private void scheduleClass(LinkedHashMap<Session, FitnessClass> schedule, FitnessClass fitnessClass) {
        schedule.put(fitnessClass.get_session(), fitnessClass);
    }

    //Books classes based on a default week
    private void book_default_classes(LinkedHashMap<Session, FitnessClass> schedule, int weekNumber) {
        scheduleClass(schedule, new ZumbaClass(weekNumber, Session.SAT_MORN));
        scheduleClass(schedule, new YogaClass(weekNumber, Session.SAT_AFTER));
        scheduleClass(schedule, new SpinClass(weekNumber, Session.SAT_EVE_1));
        scheduleClass(schedule, new BoxerciseClass(weekNumber));
        scheduleClass(schedule, new YogaClass(weekNumber, Session.SUN_MORN));
        scheduleClass(schedule, new ZumbaClass(weekNumber, Session.SUN_AFTER));
        scheduleClass(schedule, new BodysculptClass(weekNumber));
        scheduleClass(schedule, new SpinClass(weekNumber, Session.SUN_EVE_2));
    }

    //Gets a week's schedule from the yearly schedule, based on the week number
    LinkedHashMap<Session, FitnessClass> get_week_timetable(int weekNumber) {
        return yearlySchedule.get(weekNumber);
    }

    //Returns a fitness class at the specified week number and session slot
    public FitnessClass get_class(int weeknumber, Session session) {
        LinkedHashMap<Session, FitnessClass> week = get_week_timetable(weeknumber);
        return week.get(session);
    }

    //Creates a new customer and keeps a record in the customers ArrayList
    void new_customer(String name) {
        Customer newCustomer;
        newCustomer = new Customer(name);
        customers.add(newCustomer);
        System.err.println("Adding new user: " + newCustomer.get_name());
    }

    //Return a customer from customers using the customers name
    Customer get_customer(String name) {
        if (check_customer(name))
            for (Customer customer : customers) {
                if (customer.get_name().equalsIgnoreCase(name)) {
                    return customer;
                }
            }
        System.err.println("Invalid customer");
        return null;
    }

    //Checks if customer already exists
    boolean check_customer(String name) {
        for (Customer customer : customers)
            if (customer.get_name().equalsIgnoreCase(name))
                return true;
        return false;
    }

    //Booked selected customer to selected class, detailing the payment method
    void book_customer_to_class(Customer customer, FitnessClass fitnessClass, Boolean cardPayment) {
        fitnessClass.add_customer(customer, cardPayment);
        customer.add_to_class(fitnessClass);
        System.err.println("Booked " + customer + " to " + fitnessClass + " for week " +fitnessClass.get_weekNumber());
    }

    //Removes a customer from a fitness class
    public void remove_customer_from_class(Customer customer, FitnessClass fitnessClass) {
        fitnessClass.remove_customer(customer);
        customer.remove_from_class(fitnessClass);
        System.err.println("Removed " + fitnessClass + " booking for " + customer);
    }

    //Customer attends a class
    public void customer_attend_class(Customer customer, FitnessClass fitnessClass) {
        if (get_customer_classList(customer, false).contains(fitnessClass)) {
            customer.attend_class(fitnessClass);
            System.err.println(customer + " attended " + fitnessClass.getClassName());
        } else {
            System.err.println("Invalid class");
        }
    }

    //Returns a customers list of classes (Booked or attended)
    public ArrayList<FitnessClass> get_customer_classList(Customer customer, Boolean attended) {
        if (attended)
            return customer.get_attended();
        else
            return customer.get_booked();
    }

}