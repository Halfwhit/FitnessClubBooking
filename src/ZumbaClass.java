class ZumbaClass extends FitnessClass {

    private final String className;

    ZumbaClass(int weekNumber, BookingController.Session session) {
        super(weekNumber, session);
        this.className = "Zumba";
        this.classType = BookingController.ClassType.ZUMBA;
    }

    public String getClassName() {
        return className;
    }

}
