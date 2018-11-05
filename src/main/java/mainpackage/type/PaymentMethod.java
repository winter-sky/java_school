package mainpackage.type;

public enum PaymentMethod {
    WEB_MONEY("Web Money"),

    PAYPAL("Pay Pal"),

    VISA("Visa"),

    MASTERCARD("Master Card"),

    POD("POD");

    private String name;

    PaymentMethod(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return name;
    }
}
