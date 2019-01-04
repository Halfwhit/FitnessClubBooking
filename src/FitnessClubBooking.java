import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Random;

class FitnessClubBooking {

    private final BookingController bc;
    private final ReviewController rc;
    private boolean running;
    private Customer customer;
    private FitnessClass fitnessClass;

    FitnessClubBooking() {

        bc = new BookingController();
        rc = new ReviewController();
        System.out.println("Fitness Club Booking is now running...\n");
        System.out.println("Test data initialising...");
        TestData();

        running = true;
        get_customer();
        while (running) {
            while (customer != null)
                MainMenu();
        }
    }

    private void TestData() {

        //Setup test customers
        ArrayList<Customer> testCustomers = new ArrayList<>();
        Customer testCustomer;
        FitnessClass testClass;

        for (int i = 1; i < 21; i++) {
            Random rand = new Random();
            int weeknumber = rand.nextInt(51);
            int sessid = rand.nextInt(7);
            BookingController.Session[] sessions = BookingController.Session.values();
            BookingController.Session session = sessions[sessid];

            bc.new_customer(new String("Test Customer " + i));
            testCustomer = bc.get_customer("Test Customer " + i);
            testCustomers.add(testCustomer);

            testClass = bc.get_class(weeknumber, session);

            bc.book_customer_to_class(testCustomer, testClass, rand.nextBoolean());
            bc.customer_attend_class(testCustomer, testClass);
            rc.attend_class(testClass.classType);
            int rating = 1 + rand.nextInt(4);
            rc.review_class(testClass.classType, rating);
        }
        System.out.println("");
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
        //bc.print_timetable(week);
        timetable = bc.get_week_timetable(week);
        session = UI.select_session(timetable);

        fitnessClass = timetable.get(session);
    }

    private ArrayList<FitnessClass> get_customer_classes(Boolean attended) {
        ArrayList<FitnessClass> list;
        if (attended)
            list = customer.get_attended();
        else
            list = customer.get_booked();
        return list;
    }

    private void set_class_from_list(ArrayList<FitnessClass> list) {
        int i = 1;
        System.out.println("Please select from your classes:");
        for (FitnessClass fitnessClass : list) {
            System.out.println(i + ". " + fitnessClass + " Week: " + fitnessClass.get_weekNumber() + " Time: " + fitnessClass.get_session());
            i++;
        }
        fitnessClass = list.get((UI.get_menu_input() - 1));
    }

    private void clear_data() {
        fitnessClass = null;
    }

    private void book_class() {
        if (fitnessClass == null)
            set_class();
        if (fitnessClass.get_list().containsKey(customer)) {
            System.out.println(customer + " is already booked in for " + fitnessClass);
        } else {
            bc.book_customer_to_class(customer, fitnessClass, UI.card_payment());
            UI.print_classes(customer.get_name(), false, bc.get_customer_classList(customer, false));
        }
        clear_data();
    }

    private void change_class() {
        if (fitnessClass == null)
            set_class_from_list(get_customer_classes(false));

        bc.remove_customer_from_class(customer, fitnessClass);
        UI.print_classes(customer.get_name(), false, bc.get_customer_classList(customer, false));
        clear_data();
    }

    private void attend_class() {
        if (fitnessClass == null)
            set_class_from_list(get_customer_classes(false));

        bc.customer_attend_class(customer, fitnessClass);
        rc.attend_class(fitnessClass.classType);
        UI.print_classes(customer.get_name(), true, bc.get_customer_classList(customer, true));
        if (UI.review_now())
            review_class();
        else clear_data();
    }

    private void review_class() {
        if (fitnessClass == null)
            set_class_from_list(get_customer_classes(true));

            rc.review_class(fitnessClass.classType,  UI.review_class(fitnessClass));
        clear_data();
    }

    private void get_reports() {

        for (BookingController.ClassType classType : BookingController.ClassType.values()) {
            System.out.println(classType + " Attended: " + rc.get_class_attendance(classType) + " Rating: " + rc.get_average(classType));
        }
    }

    private void get_champion() {
        int value = 0;
        BookingController.ClassType champion = null;
        for (BookingController.ClassType classType : BookingController.ClassType.values()) {
            if (rc.get_class_earnings(classType) > value) {
                value = rc.get_class_earnings(classType);
                champion = classType;
            }
        }
        System.out.println("The champion class is " + champion + " with earnings of £" + value);
    }

    private boolean check_for_exit(String input) {

        return input.equals("0");
    }
}
