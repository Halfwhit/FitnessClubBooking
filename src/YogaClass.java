class YogaClass extends FitnessClass {

    private final String className;
    private final int price;

    YogaClass(int weekNumber, BookingController.Session session) {
        super(weekNumber, session);
        this.className = "Yoga";
        this.price = YOGA_PRICE;
        this.classType = BookingController.ClassType.YOGA;
    }

    public String getClassName() {
        return className;
    }

    public int getPrice() {
        return price;
    }
}
