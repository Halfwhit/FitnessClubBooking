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

    public static String get_input_string(Input input) {
        Scanner sc;
        String in;

        switch (input){
            case NAME:
                System.out.println("Please enter your name:");
        }

        sc = new Scanner(System.in);
        in = sc.next();

        return in;
    }

    public static int get_menu_input() {
        Scanner sc;
        int in;

        System.out.println("Select an option:");
        sc = new Scanner(System.in);
        in = sc.nextInt();

        return in;
    }

    public static int print_timetable() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please select a week to view: (1-52)");
        int in = sc.nextInt();
        return in;
    }

    public static void login_text() {

    }
}
