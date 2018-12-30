class BodysculptClass extends FitnessClass {

    private String className;
    private int price;

    BodysculptClass() {
        this.className = "BODYSCULPT";
        this.price = BODYSCULPT_PRICE;
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
