import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;

class UI {

    //Check for a valid week number
    private static boolean is_in_range_week(int x) {
        if (x >= 0 && x <= 52)
            return true;
        else
            return false;
    }

    //Returns user input as a string (Used to get name)
    private static String get_input_string() {
        Scanner sc;
        String in;

        sc = new Scanner(System.in);
        in = sc.next();

        return in;
    }

    //User input for numbered menus
    static int get_int_input() {
        Scanner sc;

        sc = new Scanner(System.in);
        if (sc.hasNextInt())
            return sc.nextInt();
        else
            System.out.println("Invalid selection");
            return get_int_input();
    }

    //User selects week number
    static int select_week() {
        //Based on 2019 calendar:
        System.out.flush();
        System.out.println("Weeks:\n" +
                "       1 - 4 : January\n" +
                "       5 - 8 : February\n" +
                "       9 -13 : March\n" +
                "       14-17 : April\n" +
                "       18-21 : May\n" +
                "       22-26 : June\n" +
                "       27-30 : July\n" +
                "       31-34 : August\n" +
                "       35-39 : September\n" +
                "       40-43 : October\n" +
                "       44-47 : November\n" +
                "       47-52 : December\n" +
                "Please select a week number:");
        int in = get_int_input();
        boolean valid = is_in_range_week(in);
        while (!valid) {
            System.out.println("Invalid selection");
            in = get_int_input();
            valid = is_in_range_week(in);
        }
        return in;
    }

    //User selects session slot
    public static BookingController.Session select_session(LinkedHashMap< BookingController.Session, FitnessClass > timetable, int weekNumber) {
        System.out.flush();
        System.out.println("Week " + weekNumber);
        int i = 1;
        for (BookingController.Session session : timetable.keySet()) {
            System.out.println(i++ + ". " + session + ": " + timetable.get(session));
        }
        System.out.println("\nSelect a session:");

        switch (get_int_input()) {
            case 1:
                return BookingController.Session.SAT_MORN;
            case 2:
                return BookingController.Session.SAT_AFTER;
            case 3:
                return BookingController.Session.SAT_EVE_1;
            case 4:
                return BookingController.Session.SAT_EVE_2;
            case 5:
                return BookingController.Session.SUN_MORN;
            case 6:
                return BookingController.Session.SUN_AFTER;
            case 7:
                return BookingController.Session.SUN_EVE_1;
            case 8:
                return BookingController.Session.SUN_EVE_2;
            case 0:
                return null;
            default:
                System.out.println("Invalid selection\n");
                System.out.flush();
                return select_session(timetable, weekNumber);
        }
    }

    //Main menu options
    public static int menu_main () {
        System.out.println("1. Book a class\n" +
                "2. Cancel a booking\n" +
                "3. Attend a class\n" +
                "4. Review a class\n" +
                "5. Monthly fitness class reports\n" +
                "6. Monthly champion fitness class report\n" +
                "9. Test Drive Mode \n" +
                "\n0. Logout\n");
        System.out.println("Select an option:");
        return get_int_input();
    }

    //Test drive options
    public static int test_drive_menu () {
        System.out.flush();
        System.out.println("1. Generate test data\n" +
                "2. New Customer\n" +
                "3. Book a class\n" +
                "4. Attend a class\n" +
                "5. Class reviewed\n" +
                "9. Clear review and attendance data\n" +
                "\n0. Back");
        System.out.println("Select an option");
        return get_int_input();
    }

    //Get active user
    public static String get_customer () {
        System.out.println("Enter your name, 0 to go back/exit:");
        return get_input_string();
    }

    //Print a customers class list
    public static void print_classes (String customerName, Boolean attended, ArrayList< FitnessClass > classList){
        String formattedClassList = "";
        for (FitnessClass fitnessClass : classList) {
            formattedClassList += ("\nWeek: " + fitnessClass.get_weekNumber() +
                    "    Time: " + fitnessClass.get_session() +
                    "   Class: " + fitnessClass.getClassName());
        }

        if (!attended) {
            System.out.println(customerName + " has booked the following classes:" + formattedClassList + "\n");
        } else {
            System.out.println(customerName + " has attended the following classes:" + formattedClassList + "\n");
        }
    }

    //Review options
    public static int review_class (FitnessClass fitnessClass){
        System.out.println("Please review the class:\n" +
                "1. Very dissatisfied\n" +
                "2: Dissatisfied\n" +
                "3: Ok\n" +
                "4: Satisfied\n" +
                "5: Very Satisfied");
        int in = get_int_input();
        switch (in) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                return in;
            default:
                System.out.println("Invalid selection\n");
                return review_class(fitnessClass);
        }
    }

    //Check if customer wants to leave a review after attending
    public static boolean review_now () {
        System.out.println("Would you like to leave a review of the class?\n" +
                "1. Yes\n" +
                "2. No");
        int in = get_int_input();
        switch (in) {
            case 1:
                return true;
            case 0:
            case 2:
                System.out.println("Please consider leaving a review\n");
                return false;
            default:
                System.out.println("Invalid selection");
                return review_now();
        }
    }

    //Get customer payment method
    public static Boolean card_payment () {
        System.out.println("How will you be paying?\n" +
                "1. Cash\n" +
                "2. Card");
        switch (UI.get_int_input()) {
            case 1:
                System.out.println("Paying by cash\n");
                return false;
            case 2:
                System.out.println("Paying by card\n");
                return true;
            case 0:
                System.out.println("Unable to return");
            default:
                System.out.println("Please select a valid option\n");
                return card_payment();
        }
    }

}