class BoxerciseClass extends FitnessClass {

    private String className;
    private int price;

    BoxerciseClass() {
        this.className = "BOXERCISE";
        this.price = BOXERCISE_PRICE;
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
