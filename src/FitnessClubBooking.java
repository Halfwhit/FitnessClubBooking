import java.util.LinkedHashMap;

public class FitnessClubBooking {

    private BookingController bc;
    private boolean running;

    FitnessClubBooking() {

        System.out.println("Fitness Club Booking is now running...");

        bc = new BookingController();
        running = true;

        while (running) {
            MainMenu();
        }
    }

    void MainMenu() {
        UI.menu_main();
        switch (UI.get_menu_input()) {
            case 1:
                bc.new_customer(UI.get_input_string(UI.Input.NAME));
                break;
            case 2:
                BookingMenu();
                break;
            case 3:
                DetailsMenu();
                break;
            case 0:
                running = false;
        }
    }

    void BookingMenu() {
        UI.menu_booking();
        switch (UI.get_menu_input()) {
            case 1:
                String customerName;
                Customer customer;
                LinkedHashMap<BookingController.Session, FitnessClass> timetable;
                FitnessClass fitnessClass;
                int week;
                BookingController.Session session;

                customerName = UI.get_input_string(UI.Input.NAME);

                if (!bc.check_customer(customerName))
                    bc.new_customer(customerName);
                customer = bc.get_customer(customerName);

                week = UI.select_week();
                bc.print_timetable(week);
                timetable = bc.get_week_timetable(week);
                session = UI.select_session();

                //TODO: Sign up customer for a class
                fitnessClass = timetable.get(session);
                bc.book_customer_to_class(customer, fitnessClass);
                break;
            case 0:
                running = false;
        }
    }

    void DetailsMenu() {
        UI.menu_details();
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
        UI.menu_timetable();
        switch (UI.get_menu_input()) {
            case 1:
                System.out.println(bc.get_defaultSchedule());
                break;
            case 2:
                bc.print_timetable();
                break;
            case 3:
                bc.print_timetable(UI.select_week());
        }
    }

}
