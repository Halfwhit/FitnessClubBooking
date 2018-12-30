class ZumbaClass extends FitnessClass {

    private String className;
    private int price;

    ZumbaClass() {
        this.className = "ZUMBA";
        this.price = ZUMBA_PRICE;
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
