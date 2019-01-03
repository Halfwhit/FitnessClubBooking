import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.EnumMap;

public class ReviewController {

    private final EnumMap<BookingController.ClassType, ArrayList<Integer>> reviews; //Contains all reviews for each class
    private final ArrayList<Integer> BodysculptRating;
    private final ArrayList<Integer> BoxerciseRating;
    private final ArrayList<Integer> SpinRating;
    private final ArrayList<Integer> YogaRating;
    private final ArrayList<Integer> ZumbaRating;

    ReviewController() {
        reviews = new EnumMap<>(BookingController.ClassType.class);
        BodysculptRating = new ArrayList<>();
        BoxerciseRating = new ArrayList<>();
        SpinRating = new ArrayList<>();
        YogaRating = new ArrayList<>();
        ZumbaRating = new ArrayList<>();
        reviews.put(BookingController.ClassType.BODYSCULPT, BodysculptRating);
        reviews.put(BookingController.ClassType.BOXERCISE, BoxerciseRating);
        reviews.put(BookingController.ClassType.SPIN, SpinRating);
        reviews.put(BookingController.ClassType.YOGA, YogaRating);
        reviews.put(BookingController.ClassType.ZUMBA, ZumbaRating);
    }

    public void review_class(BookingController.ClassType classType, int rating) {
        ArrayList<Integer> ratings = reviews.get(classType);
        ratings.add(rating);
    }

    public ArrayList<Integer> get_rating(BookingController.ClassType classType) {
        return reviews.get(classType);
    }
}
