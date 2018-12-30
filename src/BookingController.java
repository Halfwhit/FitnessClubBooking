import java.util.*;

public class BookingController {

    enum Session {
        SAT_MORN, SAT_AFTER, SAT_EVE_1, SAT_EVE_2, SUN_MORN, SUN_AFTER, SUN_EVE_1, SUN_EVE_2
    }

    private ArrayList<LinkedHashMap<Session, FitnessClass>> yearlySchedule;
    private LinkedHashMap<Session, FitnessClass> weeklySchedule;

    private ArrayList<Customer> customers;

    BookingController() {
        yearlySchedule = new ArrayList<LinkedHashMap<Session, FitnessClass>>();
        weeklySchedule = new LinkedHashMap<Session, FitnessClass>();
        customers = new ArrayList<Customer>();

        //Default timetable
        scheduleClass(Session.SAT_MORN,     new ZumbaClass());
        scheduleClass(Session.SAT_AFTER,    new YogaClass());
        scheduleClass(Session.SAT_EVE_1,    new SpinClass());
        scheduleClass(Session.SAT_EVE_2,    new BoxerciseClass());
        scheduleClass(Session.SUN_MORN,     new YogaClass());
        scheduleClass(Session.SUN_AFTER,    new ZumbaClass());
        scheduleClass(Session.SUN_EVE_1,    new BodysculptClass());
        scheduleClass(Session.SUN_EVE_2,    new SpinClass());

        populate_timetable();
    }

    public ArrayList get_yearlySchedule() {
        return yearlySchedule;
    }

    public LinkedHashMap get_weeklySchedule() {
        return weeklySchedule;
    }

    public void scheduleClass(Session session, FitnessClass fitnessClass) {
        weeklySchedule.put(session, fitnessClass);
    }

    // Adds the default weekly schedule to every week of the year
    private void populate_timetable() {
        for (int i = 0; i < 52; i++) {
            yearlySchedule.add(weeklySchedule);
        }
    }

    public void print_timetable() {
        for (int i = 0; i < yearlySchedule.size(); i++) {
            int weekNumber = i + 1;
            LinkedHashMap<Session, FitnessClass> week = yearlySchedule.get(i);
            System.out.println("Week: " + weekNumber + "     " + week);
        }
    }

    public void print_timetable(int weekNumber) {
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
        System.out.println("Sunday " + "Evening 2   : " + week.get(Session.SUN_EVE_2));
    }

    public void print_timetable(int startWeek, int endWeek) {
        int weeks = endWeek - startWeek;
        int weekNumber = startWeek;
        System.out.println("Printing weeks " + startWeek + " to " + endWeek);
        for (int i = 0; i <= weeks; i++, weekNumber++) {
            print_timetable(weekNumber);
        }
    }

    public FitnessClass get_class(Session session) {
        return weeklySchedule.get(session);
    }

    public void add_customer(String name) {
        Customer customer;
        String customerName;

        if (name == null) {
            System.out.println("Please enter your name:");
            customerName = UI.get_input_string();
        } else {
            customerName = name;
        }

        customer = new Customer(customerName);
        System.out.println("User created: " + customerName);
        customers.add(customer);
    }

    public boolean customer_exists(String name) {
        ArrayList<String> list = new ArrayList<String>();

        for (Customer customer : customers) {
            String customerName = customer.get_name();
            list.add(customerName);
        }

        if (list.contains(name)) {
            return true;
        } else {
            return false;
        }
    }

    public Customer get_customer_by_name(String name) {
        for (Customer customer : customers) {
            String customerName = customer.get_name();
            if (customerName.equalsIgnoreCase(name)) {
                return customer;
            }
        }

        //TODO
        return null;
    }
}