class SpinClass extends FitnessClass {

    private String className;
    private int price;

    SpinClass(int weekNumber, BookingController.Session session) {
        super(weekNumber, session);
        this.className = "Spin";
        this.price = SPIN_PRICE;
    }

    public String getClassName() {
        return className;
    }

    public int getPrice() {
        return price;
    }
}
