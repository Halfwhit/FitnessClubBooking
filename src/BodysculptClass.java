class BodysculptClass extends FitnessClass {

    private final String className;

    BodysculptClass(int weekNumber) {
        super(weekNumber, BookingController.Session.SUN_EVE_1);
        this.className = "Bodusculpt";
        this.classType = BookingController.ClassType.BODYSCULPT;
    }

    public String getClassName() {
        return className;
    }

}
