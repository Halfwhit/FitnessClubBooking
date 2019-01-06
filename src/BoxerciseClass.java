class BoxerciseClass extends FitnessClass {

    private final String className;

    BoxerciseClass(int weekNumber) {
        super(weekNumber, BookingController.Session.SAT_EVE_2);
        this.className = "Boxercise";
        this.classType = BookingController.ClassType.BOXERCISE;
    }

    public String getClassName() {
        return className;
    }

}
