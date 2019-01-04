import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;

class UI {

    private static String get_input_string() {
        Scanner sc;
        String in;

        sc = new Scanner(System.in);
        in = sc.next();

        return in;
    }

    public static int get_menu_input() {
        Scanner sc;
        int in;

        System.out.println("Select an option: ");
        sc = new Scanner(System.in);
        in = sc.nextInt();

        return in;
    }

    public static void customer_get_fitness_class() {
        System.out.println("Please choose a class");
    }

    public static int select_week() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input a week number: (1-52)");
        return (sc.nextInt() - 1);
    }

    public static BookingController.Session select_session(LinkedHashMap<BookingController.Session, FitnessClass> timetable) {
        int i = 1;
        for (BookingController.Session session : timetable.keySet()) {
            System.out.println(i++ + ". " + session + ": " + timetable.get(session));
        }

        switch (get_menu_input()) {
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
            default:
                return null;
        }

    }

    public static int menu_main() {
        System.out.println("\n1. Book a class\n" +
                "2. Cancel a booking\n" +
                "3. Attend a class\n" +
                "4. Review a class\n" +
                "5. Monthly fitness class reports\n" +
                "6. Monthly champion fitness class report\n" +
                "\n0. Logout");
        return get_menu_input();
    }

    public static String get_customer() {
        System.out.println("Enter your name, 0 to go back/exit:");
        return get_input_string();
    }

    public static void print_classes(String customerName, Boolean attended, ArrayList<FitnessClass> classList) {
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

    public static int review_class(FitnessClass fitnessClass) {
        System.out.println("Please review the class:\n" +
                "1. Very dissatisfied\n" +
                "2: Dissatisfied\n" +
                "3: Ok\n" +
                "4: Satisfied\n" +
                "5: Very Satisfied");
        return get_menu_input();
    }

    public static boolean review_now() {
        System.out.println("Would you like to leave a review of the class?\n" +
                "1. Yes\n" +
                "2. No");
        return get_menu_input() == 1;
    }

    public static BookingController.ClassType class_type() {
        System.out.println("Please select a class type:");

        int i = 0;
        for (BookingController.ClassType classType : BookingController.ClassType.values()) {
            System.out.println(i+1 + ". " + BookingController.ClassType.values()[i]);
            i++;
        }
        switch (UI.get_menu_input()) {
            case 1:
                return BookingController.ClassType.BODYSCULPT;
            case 2:
                return BookingController.ClassType.BOXERCISE;
            case 3:
                return BookingController.ClassType.SPIN;
            case 4:
                return BookingController.ClassType.YOGA;
            case 5:
                return BookingController.ClassType.ZUMBA;
            default:
                return null;
        }
    }

    public static Boolean card_payment() {
        System.out.println("How will you be paying?\n" +
                "1. Cash\n" +
                "2. Card");
        switch (UI.get_menu_input()) {
            case 1:
                System.out.println("Paying by cash");
                return false;
            case 2:
                System.out.println("Paying by card");
                return true;
            default:
                System.out.println("Please select a valid option");
                return card_payment();
        }
    }
}
