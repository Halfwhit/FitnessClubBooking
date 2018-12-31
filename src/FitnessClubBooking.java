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

        while (running) {
            MainMenu();
        }
    }

    void MainMenu() {
        switch (UI.menu_main()) {
            case 1:
                //Book a class
                book_class();
                customer.print_classes(false);
                break;
            case 2:
                //Change a class

                break;
            case 3:
                //Attend a class

                break;
            case 4:
                //Monthly fitness class report
                break;
            case 5:
                //Monthly champion fitness class report
            case 0:
                running = false;
        }
    }

    void get_customer() {
        String customerName = UI.get_name();

        if (!bc.check_customer(customerName)) {
            System.out.println("Adding new user: " + customerName);
            bc.new_customer(customerName);
        }
        customer = bc.get_customer(customerName);
        System.out.println("\nWelcome, " + customer.get_name());
    }

    void BookingMenu() {
        switch (UI.get_menu_input()) {
            case 1:
                LinkedHashMap<BookingController.Session, FitnessClass> timetable;
                int week;
                BookingController.Session session;

                week = UI.select_week();
                bc.print_timetable(week);
                timetable = bc.get_week_timetable(week);
                session = UI.select_session();

                //TODO: Sign up customer for a class
                FitnessClass fitnessClass = timetable.get(session);
                bc.book_customer_to_class(customer, fitnessClass);
                break;
            case 0:
                running = false;
        }
    }

    void DetailsMenu() {
        switch (UI.get_menu_input()) {
            case 1:
                TimetableMenu();
                break;
            case 2:
                System.out.println("//TODO");
                break;
            case 0:
                running = false;
        }
    }

    void TimetableMenu() {
        switch (UI.get_menu_input()) {
            case 1:
                System.out.println("TODO");
                break;
            case 2:
                bc.print_timetable();
                break;
            case 3:
                bc.print_timetable(UI.select_week());
        }
    }

    private void clear() {
        customer = null;
        fitnessClass = null;
    }

    void get_class() {
        LinkedHashMap<BookingController.Session, FitnessClass> timetable;
        int week;
        BookingController.Session session;

        week = UI.select_week();
        bc.print_timetable(week);
        timetable = bc.get_week_timetable(week);
        session = UI.select_session();

        fitnessClass = timetable.get(session);
    }

    void book_class() {
        if (customer == null)
            get_customer();
        if (fitnessClass == null)
            get_class();

        bc.book_customer_to_class(customer, fitnessClass);
        customer.add_to_class(fitnessClass);
        clear();
    }
}
