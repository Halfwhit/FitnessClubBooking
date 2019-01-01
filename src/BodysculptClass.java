class BodysculptClass extends FitnessClass {

    private String className;
    private int price;

    BodysculptClass(int weekNumber, BookingController.Session session) {
        super(weekNumber, session);
        this.className = "Bodusculpt";
        this.price = BODYSCULPT_PRICE;
    }

    public String getClassName() {
        return className;
    }

    public int getPrice() {
        return price;
    }
}
