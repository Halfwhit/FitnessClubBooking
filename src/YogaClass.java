class YogaClass extends FitnessClass {

    private final String className;

    YogaClass(int weekNumber, BookingController.Session session) {
        super(weekNumber, session);
        this.className = "Yoga";
        this.classType = BookingController.ClassType.YOGA;
    }

    public String getClassName() {
        return className;
    }

}
