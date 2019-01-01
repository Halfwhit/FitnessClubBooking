class ZumbaClass extends FitnessClass {

    private String className;
    private int price;

    ZumbaClass(int weekNumber, BookingController.Session session) {
        super(weekNumber, session);
        this.className = "Zumba";
        this.price = ZUMBA_PRICE;
    }

    public String getClassName() {
        return className;
    }

    public int getPrice() {
        return price;
    }
}
