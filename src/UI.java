import java.util.Scanner;

public class UI {

    enum Input {
        NAME
    }

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

        System.out.println("Select an option, or zero to exit:");
        sc = new Scanner(System.in);
        in = sc.nextInt();

        return in;
    }

    public static int select_week() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nPlease select a week: (1-52)");
        int in = sc.nextInt();
        return in;
    }

    public static BookingController.Session select_session() {
        System.out.println("\n1. Saturday Morning\n" +
                             "2. Saturday Afternoon\n" +
                             "3. Saturday Evening 1\n" +
                             "4. Saturday Evening 2\n" +
                             "5. Sunday Morning\n" +
                             "6. Sunday Afternoon\n" +
                             "7. Sunday Evening 1\n" +
                             "8. Sunday Evening 2");
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
        }
        return null;
    }

    public static int menu_main() {
        System.out.println("\n1. Book a class\n" +
                "2. Change a class\n" +
                "3. Attend a class\n" +
                "4. Monthly fitness class reports\n" +
                "5. Monthly champion fitness class report");
        return get_menu_input();
    }

    public static String get_name() {
        System.out.println("\nPlease enter your name:");
        return get_input_string();
    }
}
