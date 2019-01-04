import java.util.ArrayList;
import java.util.EnumMap;

class ReviewController {
    private final EnumMap<BookingController.ClassType, Integer> attendanceNumbers;
    private final EnumMap<BookingController.ClassType, ArrayList<Integer>> reviews; //Contains all reviews for each class

    ReviewController() {
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

        System.out.println("Review Controller initialised");
    }

    public void review_class(BookingController.ClassType classType, int rating) {
        ArrayList<Integer> ratings = reviews.get(classType);
        ratings.add(rating);
    }

    private ArrayList<Integer> get_rating(BookingController.ClassType classType) {
        return reviews.get(classType);
    }

    public float get_average(BookingController.ClassType classType) {
        float sum = 0;
        ArrayList<Integer> reviews = get_rating(classType);
        for (Integer review : reviews) {
            sum += review;
        }
        return (sum / (reviews.size()));
    }

    public void attend_class(BookingController.ClassType classType) {
        attendanceNumbers.put(classType, attendanceNumbers.get(classType) + 1);
    }

    public int get_class_attendance(BookingController.ClassType classType) {
        return attendanceNumbers.get(classType);
    }

    public int get_class_earnings(BookingController.ClassType classType) {
        int attended = get_class_attendance(classType);
        switch (classType) {
            case BODYSCULPT:
                attended *= FitnessClass.BODYSCULPT_PRICE;
            case BOXERCISE:
                attended *= FitnessClass.BOXERCISE_PRICE;
            case SPIN:
                attended *= FitnessClass.SPIN_PRICE;
            case YOGA:
                attended *= FitnessClass.YOGA_PRICE;
            case ZUMBA:
                attended *= FitnessClass.ZUMBA_PRICE;
        }
        return attended;
    }
}
