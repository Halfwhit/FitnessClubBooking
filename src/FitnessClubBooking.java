public class FitnessClubBooking {

    private BookingController bc;
    private boolean running;

    FitnessClubBooking() {

        bc = new BookingController();
        running = true;

        System.out.println("Fitness Club Booking is now running...");
        while (running) {
            MainMenu();
        }
    }

    void MainMenu() {
        System.out.println("1. Create new user\n" +
                           "2. Book a class\n" +
                           "3. Exit");
        switch (UI.get_menu_input()) {
            case 1:
                bc.add_customer(UI.get_input_string(UI.Input.NAME));
                break;
            case 2:
                //BookingMenu();
                break;
            case 3:
                running = false;
        }
    }

    void BookingMenu() {
        System.out.println("1. Book test class");
        switch (UI.get_menu_input()) {
            case 1:
                //TODO: Pick back up here - book a customer to a class - test case pass both
                bc.get_class(BookingController.Session.SAT_MORN);
        }
    }

}
