import java.util.*;

public class BookingController {

    enum Session {
        SAT_MORN, SAT_AFTER, SAT_EVE_1, SAT_EVE_2, SUN_MORN, SUN_AFTER, SUN_EVE_1, SUN_EVE_2
    }

    private ArrayList<HashMap<Session, FitnessClass>> yearlySchedule;
    private HashMap<Session, FitnessClass> weeklySchedule;

    BookingController() {
        yearlySchedule = new ArrayList<HashMap<Session, FitnessClass>>();
        weeklySchedule = new HashMap<Session, FitnessClass>();
        for (Session session : Session.values()) {
            weeklySchedule.put(session, null);
        }

        //Default timetable
        scheduleClass(Session.SAT_MORN, new ZumbaClass());
        scheduleClass(Session.SAT_AFTER, new YogaClass());
        scheduleClass(Session.SAT_EVE_1, new SpinClass());
        scheduleClass(Session.SAT_EVE_2, new BoxerciseClass());
        scheduleClass(Session.SUN_MORN, new YogaClass());
        scheduleClass(Session.SUN_AFTER, new ZumbaClass());
        scheduleClass(Session.SUN_EVE_1, new BodysculptClass());
        scheduleClass(Session.SUN_EVE_2, new SpinClass());

        populate_timetable();
    }

    public void scheduleClass(Session session, FitnessClass fitnessClass) {
        weeklySchedule.replace(session, fitnessClass);
    }

    // Adds the default weekly schedule to every week of the year
    private void populate_timetable() {
        for (int i = 0; i < 52; i++) {
            yearlySchedule.add(weeklySchedule);
        }
    }
}
