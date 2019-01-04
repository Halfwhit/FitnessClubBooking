import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Random;

public class BookingController {

    public enum  ClassType {
        BODYSCULPT, BOXERCISE, SPIN, YOGA, ZUMBA
    }

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
    private final ArrayList<Customer> customers; //Contains all registered users TODO: is this needed?

    public BookingController() {
        yearlySchedule = new ArrayList<>();
        customers = new ArrayList<>();

        populate_yearlySchedule();

        System.out.println("Booking Controller initialised");
    }

    // Adds the default weekly schedule to every week of the year
    private void populate_yearlySchedule() {
        for (int i = 0; i < 52; i++) {
            LinkedHashMap<Session, FitnessClass> schedule;
            int weekNumber = i + 1;
            schedule = new LinkedHashMap<>();

            book_default_classes(schedule, weekNumber);
            yearlySchedule.add(schedule);
        }
    }

    private void scheduleClass(LinkedHashMap<Session, FitnessClass> schedule, FitnessClass fitnessClass) {
        schedule.put(fitnessClass.get_session(), fitnessClass);
    }

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

    void print_timetable(int weekNumber) {
        LinkedHashMap<Session, FitnessClass> week = yearlySchedule.get(weekNumber);

        System.out.println("-------------------------------");
        System.out.println("Week: " + (weekNumber +1));
        System.out.println("Saturday " + "Morning   : " + week.get(Session.SAT_MORN));
        System.out.println("Saturday " + "Afternoon : " + week.get(Session.SAT_AFTER));
        System.out.println("Saturday " + "Evening 1 : " + week.get(Session.SAT_EVE_1));
        System.out.println("Saturday " + "Evening 2 : " + week.get(Session.SAT_EVE_2));
        System.out.println("Sunday " + "Morning     : " + week.get(Session.SUN_MORN));
        System.out.println("Sunday " + "Afternoon   : " + week.get(Session.SUN_AFTER));
        System.out.println("Sunday " + "Evening 1   : " + week.get(Session.SUN_EVE_1));
        System.out.println("Sunday " + "Evening 2   : " + week.get(Session.SUN_EVE_2) + "\n");
    }

    LinkedHashMap<Session, FitnessClass> get_week_timetable(int weekNumber) {
        return yearlySchedule.get(weekNumber);
    }

    public FitnessClass get_class(int weeknumber, Session session) {
        LinkedHashMap<Session, FitnessClass> week = get_week_timetable(weeknumber);
        return week.get(session);
    }

    void new_customer(String name) {
        Customer newCustomer;
        newCustomer = new Customer(name);
        customers.add(newCustomer);
    }

    Customer get_customer(String name) {
        for (Customer customer : customers) {
            if (customer.get_name().equalsIgnoreCase(name)) {
                return customer;
            }
        }
        //TODO: Error check getting of customers
        return null;
    }

    boolean check_customer(String name) {
        for (Customer customer : customers)
            if (customer.get_name().equalsIgnoreCase(name))
                return true;
        return false;
    }

    void book_customer_to_class(Customer customer, FitnessClass fitnessClass, Boolean cardPayment) {
        fitnessClass.add_customer(customer, cardPayment);
        customer.add_to_class(fitnessClass);
        System.out.println("Booked " + customer + " to " + fitnessClass + " for week " +fitnessClass.get_weekNumber());
    }

    public void remove_customer_from_class(Customer customer, FitnessClass fitnessClass) {
        fitnessClass.remove_customer(customer);
        customer.remove_from_class(fitnessClass);
        System.out.println("Cancled " + fitnessClass + " booking for " + customer);
    }

    public void customer_attend_class(Customer customer, FitnessClass fitnessClass) {
        if (get_customer_classList(customer, false).contains(fitnessClass)) {
            customer.attend_class(fitnessClass);
            System.out.println(customer + " attended " + fitnessClass.getClassName());
        } else {
            //TODO: Error check
            System.out.println("Invalid class");
        }
    }

    public ArrayList<FitnessClass> get_customer_classList(Customer customer, Boolean attended) {
        if (attended)
            return customer.get_attended();
        else
            return customer.get_booked();
    }
}