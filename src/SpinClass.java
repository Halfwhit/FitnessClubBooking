class SpinClass extends FitnessClass {

    private final String className;
    private final int price;

    SpinClass(int weekNumber, BookingController.Session session) {
        super(weekNumber, session);
        this.className = "Spin";
        this.price = SPIN_PRICE;
        this.classType = BookingController.ClassType.SPIN;
    }

    public String getClassName() {
        return className;
    }

    public int getPrice() {
        return price;
    }
}
