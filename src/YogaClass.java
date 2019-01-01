class YogaClass extends FitnessClass {

    private String className;
    private int price;

    YogaClass(int weekNumber, BookingController.Session session) {
        super(weekNumber, session);
        this.className = "Yoga";
        this.price = YOGA_PRICE;
    }

    public String getClassName() {
        return className;
    }

    public int getPrice() {
        return price;
    }
}
