import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

class FitnessClubBooking {

    private final BookingController bc;
    private final ReviewController rc;
    private boolean running;
    private Customer customer;
    private FitnessClass fitnessClass;

    FitnessClubBooking() {

        System.out.println("Fitness Club Booking is now running...");

        bc = new BookingController();
        rc = new ReviewController();

        running = true;
        get_customer();
        while (running) {
            while (customer != null)
                MainMenu();
        }
    }

    private void MainMenu() {
        switch (UI.menu_main()) {
            case 1:
                //Book a class
                book_class();
                break;
            case 2:
                //Change a class
                change_class();
                clear_data(); //TODO: Move to change_class() when done debugging
                break;
            case 3:
                //Attend a class
                attend_class();
                break;
            case 4:
                //Review a class
                review_class();
                break;
            case 5:
                //Monthly fitness class report
                get_reports();
                break;
            case 6:
                //Monthly champion fitness class report
                get_champion();
                break;
            case 0:
                customer = null;
                get_customer();
        }
    }

    private void get_customer() {

        String customerName = UI.get_customer();

        if (check_for_exit(customerName)) {
            running = false;
            return;
        }

        if (!bc.check_customer(customerName)) {
            System.out.println("Adding new user: " + customerName);
            bc.new_customer(customerName);
        }
        customer = bc.get_customer(customerName);
        System.out.println("\nWelcome, " + customer.get_name());
    }

    private void set_class() {
        LinkedHashMap<BookingController.Session, FitnessClass> timetable;
        int week;
        BookingController.Session session;

        UI.customer_get_fitness_class();
        week = UI.select_week();
        bc.print_timetable(week); //TODO: Is this needed?
        timetable = bc.get_week_timetable(week);
        session = UI.select_session();

        fitnessClass = timetable.get(session);
    }

    private void clear_data() {
        fitnessClass = null;
    }

    private void book_class() {
        if (fitnessClass == null)
            set_class();
        bc.book_customer_to_class(customer, fitnessClass);
        UI.print_classes(customer.get_name(), false, bc.get_customer_classList(customer, false));
        clear_data();
    }

    private void change_class() {
        if (fitnessClass == null)
            set_class();

        bc.remove_customer_from_class(customer, fitnessClass);
        UI.print_classes(customer.get_name(), false, bc.get_customer_classList(customer, false));
        clear_data();
    }

    private void attend_class() {
        if (fitnessClass == null)
            set_class();

        bc.customer_attend_class(customer, fitnessClass);
        UI.print_classes(customer.get_name(), true, bc.get_customer_classList(customer, true));
        if (UI.review_now())
            review_class();
        else clear_data();
    }

    private void review_class() {
        ArrayList<FitnessClass> classList;
        if (fitnessClass == null)
            set_class();
        classList = bc.get_customer_classList(customer, true);
        if (classList.contains(fitnessClass))
            rc.review_class(fitnessClass.classType,  UI.review_class(fitnessClass));
        clear_data();
    }

    private void get_reports() {

        int averageRating;
        BookingController.ClassType classType = UI.class_type();

        System.out.println(rc.get_rating(classType));
        System.out.println("The average rating for " + classType + " is "); //TODO get average
    }

    private void get_champion() {}

    private boolean check_for_exit(String input) {

        return input.equals("0");
    }
}
