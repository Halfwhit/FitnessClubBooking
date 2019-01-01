import java.util.ArrayList;
import java.util.Scanner;

public class UI {

    public static String get_input_string() {
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
        int in = sc.nextInt();
        return in;
    }

    public static BookingController.Session select_session() {
        int i = 1;
        for (BookingController.Session session : BookingController.Session.values()) {
            System.out.println(i++ + ". " + session);
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
                "4. Monthly fitness class reports\n" +
                "5. Monthly champion fitness class report\n" +
                "\n0. Logout");
        return get_menu_input();
    }

    public static String get_customer() {
        System.out.println("Enter your name, 0 to go back/exit:");
        return get_input_string();
    }

    public static void print_classes(String customerName, Boolean attended, ArrayList<FitnessClass> classList) {
        String formattedClassList = "\n";

        for (FitnessClass fitnessClass : classList) {
            formattedClassList = "\nWeek: " + fitnessClass.get_weekNumber() +
                                 "    Time: " + fitnessClass.get_session() +
                                 "   Class: " + fitnessClass.getClassName() + formattedClassList;
        }

        if (attended == false) {
            System.out.println(customerName + " has booked the following classes:" + formattedClassList);
        } else if (attended == true) {
            System.out.println( customerName + " has attended the following classes:" + formattedClassList);
        }
    }
}
