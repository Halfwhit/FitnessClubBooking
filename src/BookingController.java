import java.util.*;

public class BookingController {

    public enum Session {
        SAT_MORN {
            public String toString() {
                return "Saturday Morning";
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
                return "Sunday Morning";
            }
        },
        SUN_AFTER {
            public String toString() {
                return "Sunday Afternoon";
            }
        },
        SUN_EVE_1 {
            public String toString() {
                return "Sunday Evening 1";
            }
        },
        SUN_EVE_2 {
            public String toString() {
                return "Sunday Evening 2";
            }
        }
    }

    private ArrayList<LinkedHashMap<Session, FitnessClass>> yearlySchedule;
    private LinkedHashMap<Session, FitnessClass> defaultSchedule;
    private ArrayList<Customer> customers;

    BookingController() {
        yearlySchedule = new ArrayList<>();
        customers = new ArrayList<>();

        populate_yearlySchedule();
    }

    private void book_default_classes(LinkedHashMap<Session, FitnessClass> schedule, int weekNumber) {
        scheduleClass(schedule, new ZumbaClass(weekNumber, Session.SAT_MORN));
        scheduleClass(schedule, new YogaClass(weekNumber, Session.SAT_AFTER));
        scheduleClass(schedule, new SpinClass(weekNumber, Session.SAT_EVE_1));
        scheduleClass(schedule, new BoxerciseClass(weekNumber, Session.SAT_EVE_2));
        scheduleClass(schedule, new YogaClass(weekNumber, Session.SUN_MORN));
        scheduleClass(schedule, new ZumbaClass(weekNumber, Session.SUN_AFTER));
        scheduleClass(schedule, new BodysculptClass(weekNumber, Session.SUN_EVE_1));
        scheduleClass(schedule, new SpinClass(weekNumber, Session.SUN_EVE_2));
    }

    private void scheduleClass(LinkedHashMap<Session, FitnessClass> schedule, FitnessClass fitnessClass) {
        schedule.put(fitnessClass.get_session(), fitnessClass);
    }

    // Adds the default weekly schedule to every week of the year
    private void populate_yearlySchedule() {
        for (int i = 0; i < 52; i++) {
            LinkedHashMap<Session, FitnessClass> schedule;
            int weekNumber = i + 1;
            schedule = new LinkedHashMap<Session, FitnessClass>();

            book_default_classes(schedule, weekNumber);
            yearlySchedule.add(schedule);
        }
    }

    void print_timetable() {
        for (int i = 0; i < yearlySchedule.size(); i++) {
            int weekNumber = i + 1;
            LinkedHashMap<Session, FitnessClass> week = yearlySchedule.get(i);
            System.out.println("Week: " + weekNumber + "     " + week);
        }
    }

    void print_timetable(int weekNumber) {
        LinkedHashMap<Session, FitnessClass> week = yearlySchedule.get(weekNumber - 1);

        System.out.println("-------------------------------");
        System.out.println("Week: " + weekNumber);
        System.out.println("Saturday " + "Morning   : " + week.get(Session.SAT_MORN));
        System.out.println("Saturday " + "Afternoon : " + week.get(Session.SAT_AFTER));
        System.out.println("Saturday " + "Evening 1 : " + week.get(Session.SAT_EVE_1));
        System.out.println("Saturday " + "Evening 2 : " + week.get(Session.SAT_EVE_2));
        System.out.println("Sunday " + "Morning     : " + week.get(Session.SUN_MORN));
        System.out.println("Sunday " + "Afternoon   : " + week.get(Session.SUN_AFTER));
        System.out.println("Sunday " + "Evening 1   : " + week.get(Session.SUN_EVE_1));
        System.out.println("Sunday " + "Evening 2   : " + week.get(Session.SUN_EVE_2) +"\n");
    }

    public void print_timetable(int startWeek, int endWeek) {
        int weeks = endWeek - startWeek;
        int weekNumber = startWeek;
        System.out.println("Printing weeks " + startWeek + " to " + endWeek);
        for (int i = 0; i <= weeks; i++, weekNumber++) {
            print_timetable(weekNumber);
        }
    }

    LinkedHashMap<Session, FitnessClass> get_week_timetable(int weekNumber) {
        return yearlySchedule.get(weekNumber);
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
        for (Customer customer : customers) {
            if (customer.get_name().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    void book_customer_to_class(Customer customer, FitnessClass fitnessClass) {
        fitnessClass.add_customer(customer);
        customer.add_to_class(fitnessClass);
        System.out.println("Booked " + customer + " to " + fitnessClass);
    }

    public void remove_customer_from_class(Customer customer, FitnessClass fitnessClass) {
        fitnessClass.remove_customer(customer);
        customer.remove_from_class(fitnessClass);
        System.out.println("Cancled " + fitnessClass + " booking for " + customer);
    }

    public void customer_attend_class(Customer customer, FitnessClass fitnessClass) {
        customer.attend_class(fitnessClass);
    }

    public ArrayList<FitnessClass> get_customer_classList(Customer customer, Boolean attended) {
        return customer.get_classList(attended);
    }
}