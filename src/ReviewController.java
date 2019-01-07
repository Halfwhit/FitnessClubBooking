import java.util.ArrayList;
import java.util.EnumMap;

class ReviewController {

    //Attendance numbers based on class type
    private EnumMap<BookingController.ClassType, Integer> attendanceNumbers;

    //Contains all reviews for each class type
    private EnumMap<BookingController.ClassType, ArrayList<Integer>> reviews;

    //Constructor
    ReviewController() {
        build_storage();
        System.err.println("Review Controller initialised");
    }

    //Populate EnumMaps with defaults
    private void build_storage() {
        reviews = new EnumMap<>(BookingController.ClassType.class);
        reviews.put(BookingController.ClassType.BODYSCULPT, new ArrayList<>());
        reviews.put(BookingController.ClassType.BOXERCISE, new ArrayList<>());
        reviews.put(BookingController.ClassType.SPIN, new ArrayList<>());
        reviews.put(BookingController.ClassType.YOGA, new ArrayList<>());
        reviews.put(BookingController.ClassType.ZUMBA, new ArrayList<>());

        attendanceNumbers = new EnumMap<>(BookingController.ClassType.class);
        attendanceNumbers.put(BookingController.ClassType.BODYSCULPT, 0);
        attendanceNumbers.put(BookingController.ClassType.BOXERCISE, 0);
        attendanceNumbers.put(BookingController.ClassType.SPIN, 0);
        attendanceNumbers.put(BookingController.ClassType.YOGA, 0);
        attendanceNumbers.put(BookingController.ClassType.ZUMBA, 0);
    }

    //Add a review for a class type
    public void review_class(BookingController.ClassType classType, int rating) {
        ArrayList<Integer> ratings = reviews.get(classType);
        ratings.add(rating);
    }

    //Get the rating for a class type
    private ArrayList<Integer> get_rating(BookingController.ClassType classType) {
        return reviews.get(classType);
    }

    //Calculate average rating for a class type
    public float get_average(BookingController.ClassType classType) {
        float sum = 0;
        ArrayList<Integer> reviews = get_rating(classType);
        for (Integer review : reviews) {
            sum += review;
        }
        if (sum == 0) {
            return 0;
        } else
            return (sum / (reviews.size()));
    }

    //Increases the attendance of a class type by one
    public void attend_class(BookingController.ClassType classType) {
        attendanceNumbers.put(classType, attendanceNumbers.get(classType) + 1);
    }

    //Returns the attendance numbers for a class type
    public int get_class_attendance(BookingController.ClassType classType) {
        return attendanceNumbers.get(classType);
    }

    //Return the earnings of a class type based on price and attendance
    public int get_earnings(BookingController.ClassType classType) {
        int a = attendanceNumbers.get(classType);
        int p;
        switch (classType) {
            case BODYSCULPT:
                p = FitnessClass.BODYSCULPT_PRICE;
                return a*p;
            case BOXERCISE:
                p = FitnessClass.BOXERCISE_PRICE;
                return a*p;
            case SPIN:
                p = FitnessClass.SPIN_PRICE;
                return a*p;
            case YOGA:
                p = FitnessClass.YOGA_PRICE;
                return a*p;
            case ZUMBA:
                p = FitnessClass.ZUMBA_PRICE;
                return a*p;
        }
        return 0;
    }

    //Allows the ReviewController data to be cleared and rebuilt
    public void reset_all_data() {
        attendanceNumbers.clear();
        reviews.clear();
        build_storage();
    }

}
