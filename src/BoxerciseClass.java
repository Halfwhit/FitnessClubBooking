class BoxerciseClass extends FitnessClass {

    private final String className;
    private final int price;

    BoxerciseClass(int weekNumber) {
        super(weekNumber, BookingController.Session.SAT_EVE_2);
        this.className = "Boxercise";
        this.price = BOXERCISE_PRICE;
        this.classType = BookingController.ClassType.BOXERCISE;
    }

    public String getClassName() {
        return className;
    }

    public int getPrice() {
        return price;
    }
}
