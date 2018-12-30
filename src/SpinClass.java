class SpinClass extends FitnessClass {

    private String className;
    private int price;

    SpinClass() {
        this.className = "SPIN";
        this.price = SPIN_PRICE;
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
