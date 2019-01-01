import java.util.LinkedHashMap;

public class FitnessClubBooking {

    private BookingController bc;
    private boolean running;
    private Customer customer;
    private FitnessClass fitnessClass;

    FitnessClubBooking() {

        System.out.println("Fitness Club Booking is now running...");

        bc = new BookingController();

        running = true;
        get_customer();
        while (running) {
            while (customer != null)
                MainMenu();
        }
    }

    void MainMenu() {
        switch (UI.menu_main()) {
            case 1:
                //Book a class
                book_class();
                clear_data(); //TODO: Move to book_class() when done debugging
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
                //Monthly fitness class report
                break;
            case 5:
                //Monthly champion fitness class report
            case 0:
                customer = null;
                get_customer();
        }
    }

    void get_customer() {

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

    void set_class() {
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

    void book_class() {
        if (fitnessClass == null)
            set_class();
        bc.book_customer_to_class(customer, fitnessClass);
        UI.print_classes(customer.get_name(), false, bc.get_customer_classList(customer, false));
        clear_data();
    }

    void change_class() {
        if (fitnessClass == null)
            set_class();

        bc.remove_customer_from_class(customer, fitnessClass);
        UI.print_classes(customer.get_name(), false, bc.get_customer_classList(customer, false));
        clear_data();
    }

    void attend_class() {
        if (fitnessClass == null)
            set_class();

        bc.customer_attend_class(customer, fitnessClass);
        UI.print_classes(customer.get_name(), true, bc.get_customer_classList(customer, true));
        clear_data();
    }

    private boolean check_for_exit(String input) {

        if (input.equals("0")) {
            return true;
        }
        return false;
    }
}
