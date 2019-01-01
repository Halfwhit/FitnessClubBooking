class BoxerciseClass extends FitnessClass {

    private String className;
    private int price;

    BoxerciseClass(int weekNumber, BookingController.Session session) {
        super(weekNumber, session);
        this.className = "Boxercise";
        this.price = BOXERCISE_PRICE;
    }

    public String getClassName() {
        return className;
    }

    public int getPrice() {
        return price;
    }
}
