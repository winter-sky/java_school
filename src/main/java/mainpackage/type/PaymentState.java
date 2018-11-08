package mainpackage.type;

public enum PaymentState {
    AWAITING_PAYMENT("Payment awaiting"),
    PAID("Paid");

    private String name;

    PaymentState (String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
