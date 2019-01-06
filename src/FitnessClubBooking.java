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
        System.out.println("\nFitness Club Booking is now running...\n");

        running = true;
        get_customer();
        while (running) {
            while (customer != null)
                MainMenu();
        }
    }

    private void TestData() {

        //Setup test data
        ArrayList<Customer> testCustomers = new ArrayList<>();
        Customer testCustomer;
        FitnessClass testClass;
        Random rand = new Random();

        boolean test_menu = true;
        while (test_menu) {
            switch (UI.test_drive_menu()) {
                case 1:
                    for (int i = 1; i < 21; i++) {
                        int weeknumber = rand.nextInt(51);
                        int sessid = rand.nextInt(7);
                        BookingController.Session[] sessions = BookingController.Session.values();
                        BookingController.Session session = sessions[sessid];

                        bc.new_customer(("Test Customer " + i));
                        testCustomer = bc.get_customer("Test Customer " + i);
                        testCustomers.add(testCustomer);

                        testClass = bc.get_class(weeknumber, session);
                        bc.book_customer_to_class(testCustomer, testClass, rand.nextBoolean());

                        bc.customer_attend_class(testCustomer, testClass);
                        rc.attend_class(testClass.classType);

                        int rating = 1 + rand.nextInt(4);
                        rc.review_class(testClass.classType, rating);
                        bc.remove_customer_from_class(testCustomer, testClass);
                    }
                    testCustomer = null;
                    testClass = null;
                    System.out.println("\n" + testCustomers.size() + " Test customers created.\n" +
                            "Each test customer has booked and attended one random scheduled fitness class.\n" +
                            "Each customer left a random rating for the class they attended.\n");
                    break;
                case 2:
                    System.out.println();
                    int customerNumber = testCustomers.size() + 1;
                    bc.new_customer("Test Customer " + customerNumber);
                    testCustomer = bc.get_customer("Test Customer " + customerNumber);
                    testCustomers.add(testCustomer);
                    break;
                case 3:
                    if (testCustomers.isEmpty()) {
                        System.err.println("No test customers available");
                        break;
                    }
                    int customerId = rand.nextInt(testCustomers.size());
                    int weeknumber = rand.nextInt(51);
                    int sessid = rand.nextInt(7);
                    BookingController.Session[] sessions = BookingController.Session.values();
                    BookingController.Session session = sessions[sessid];
                    testCustomer = testCustomers.get(customerId);
                    testClass = bc.get_class(weeknumber, session);
                    bc.book_customer_to_class(testCustomer, testClass, rand.nextBoolean());
                    break;
                case 4:
                    boolean attendable = false;
                    for (Customer tester : testCustomers) {
                        if (!tester.get_booked().isEmpty())
                            attendable = true;
                    }
                    if (!attendable) {
                        System.err.println("No test customers with classes booked");
                        break;
                    } else {
                        customerId = rand.nextInt(testCustomers.size());
                        testCustomer = testCustomers.get(customerId);
                        while (testCustomer.get_booked().isEmpty()) {
                            customerId = rand.nextInt(testCustomers.size());
                            testCustomer = testCustomers.get(customerId);
                        }
                        ArrayList<FitnessClass> booked = testCustomer.get_booked();
                        testClass = booked.get(rand.nextInt(booked.size()));

                        bc.customer_attend_class(testCustomer, testClass);
                        rc.attend_class(testClass.classType);
                        break;
                    }
                case 5:
                    boolean reviewable = false;
                    for (Customer tester : testCustomers) {
                        if (!tester.get_attended().isEmpty())
                            reviewable = true;
                    }
                    if (!reviewable) {
                        System.err.println("No test customers with classes attended");
                        break;
                    } else {
                        customerId = rand.nextInt(testCustomers.size());
                        testCustomer = testCustomers.get(customerId);
                        while (testCustomer.get_attended().isEmpty()) {
                            customerId = rand.nextInt(testCustomers.size());
                            testCustomer = testCustomers.get(customerId);
                        }
                        ArrayList<FitnessClass> attended = testCustomer.get_attended();
                        testClass = attended.get(rand.nextInt(attended.size()));

                        bc.remove_customer_from_class(testCustomer, testClass);
                        System.err.println("    due to " + testCustomer + " leaving a review");

                        int rating = 1 + rand.nextInt(4);
                        rc.review_class(testClass.classType, rating);
                        break;
                    }
                case 9:
                    System.err.println("All ReviewController data removed");
                    rc.reset_all_data();
                    break;
                case 0:
                    test_menu = false;
                    break;
                default:
                    System.out.println("Invalid selection.\n");
            }
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
            case 9:
                //Test Data
                TestData();
                break;
            case 0:
                customer = null;
                get_customer();
                break;
            default:
                System.out.println("Invalid selection.\n");
        }
    }

    private void get_customer() {

        String customerName = UI.get_customer();

        if (check_for_exit(customerName)) {
            running = false;
            return;
        }

        if (!bc.check_customer(customerName)) {
            bc.new_customer(customerName);
        }
        customer = bc.get_customer(customerName);
        System.out.println("\nWelcome, " + customer.get_name());
    }

    private void set_class() {
        LinkedHashMap<BookingController.Session, FitnessClass> timetable;
        int week;
        BookingController.Session session;

        week = UI.select_week();
        if (week == 0) {
            fitnessClass = null;
            return;
        }
        int weekIndex = week - 1;
        timetable = bc.get_week_timetable(weekIndex);
        session = UI.select_session(timetable, week);

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
        if (list.isEmpty()) {
            System.out.println("No classes to display\n");
            return;
        }
        int i = 1;
        System.out.println("Please select from your classes:");
        for (FitnessClass fitnessClass : list) {
            System.out.println(i + ". " + fitnessClass + " Week: " + fitnessClass.get_weekNumber() + " Time: " + fitnessClass.get_session());
            i++;
        }
        int selection = UI.get_int_input();
        while (!(selection <= list.size())) {
            System.out.println("Invalid selection");
            selection = UI.get_int_input();
        }
        if (selection == 0) {
            System.out.println("Returning...\n");
            fitnessClass = null;
        } else
            fitnessClass = list.get(selection - 1);
    }

    private void clear_data() {
        fitnessClass = null;
    }

    private void book_class() {
        set_class();
        if (fitnessClass == null)
            return;
        if (fitnessClass.get_list().containsKey(customer)) {
            System.out.println(customer + " is already booked in for " + fitnessClass);
        } else {
            bc.book_customer_to_class(customer, fitnessClass, UI.card_payment());
            UI.print_classes(customer.get_name(), false, bc.get_customer_classList(customer, false));
        }
        clear_data();
    }

    private void change_class() {
        set_class_from_list(get_customer_classes(false));
        if (fitnessClass == null)
            return;

        bc.remove_customer_from_class(customer, fitnessClass);
        UI.print_classes(customer.get_name(), false, bc.get_customer_classList(customer, false));
        clear_data();
    }

    private void attend_class() {
        set_class_from_list(get_customer_classes(false));
        if (fitnessClass == null)
            return;

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
        if (fitnessClass == null)
            return;

        rc.review_class(fitnessClass.classType,  UI.review_class(fitnessClass));
        //Once class is reviewed, remove it
        bc.remove_customer_from_class(customer, fitnessClass);
        System.err.println("    due to " + customer + " leaving a review");
        clear_data();
    }

    private void get_reports() {
        System.out.println();
        for (BookingController.ClassType classType : BookingController.ClassType.values()) {
            System.out.println(classType + ":");
            System.out.println("            " + " Attended: " + rc.get_class_attendance(classType) + " Rating: " + rc.get_average(classType));
        }
        System.out.println();
    }

    private void get_champion() {
        System.out.println();
        for (BookingController.ClassType classType : BookingController.ClassType.values()) {
            System.out.println(classType + ":");
            System.out.println("            " + "£" + (rc.get_earnings(classType)));
        }
        System.out.println();
    }

    private boolean check_for_exit(String input) {
        return input.equals("0");
    }
}
