class SpinClass extends FitnessClass {

    private final String className;

    SpinClass(int weekNumber, BookingController.Session session) {
        super(weekNumber, session);
        this.className = "Spin";
        this.classType = BookingController.ClassType.SPIN;
    }

    public String getClassName() {
        return className;
    }

}
