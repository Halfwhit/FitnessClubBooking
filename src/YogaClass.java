class YogaClass extends FitnessClass {

    private String className;
    private int price;

    YogaClass() {
        this.className = "YOGA";
        this.price = YOGA_PRICE;
    }

    public String getClassName() {
        return className;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return className;
    }
}
