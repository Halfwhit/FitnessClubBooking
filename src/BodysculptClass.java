class BodysculptClass extends FitnessClass {

    private final String className;
    private final int price;

    BodysculptClass(int weekNumber) {
        super(weekNumber, BookingController.Session.SUN_EVE_1);
        this.className = "Bodusculpt";
        this.price = BODYSCULPT_PRICE;
        this.classType = BookingController.ClassType.BODYSCULPT;
    }

    public String getClassName() {
        return className;
    }

    public int getPrice() {
        return price;
    }
}
