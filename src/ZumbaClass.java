class ZumbaClass extends FitnessClass {

    private final String className;
    private final int price;

    ZumbaClass(int weekNumber, BookingController.Session session) {
        super(weekNumber, session);
        this.className = "Zumba";
        this.price = ZUMBA_PRICE;
        this.classType = BookingController.ClassType.ZUMBA;
    }

    public String getClassName() {
        return className;
    }

    public int getPrice() {
        return price;
    }
}
