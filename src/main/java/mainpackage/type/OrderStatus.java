package mainpackage.type;

public enum OrderStatus {
    PAYMENT_AWAITING("Payment awaiting"),
    AWAITING_SHIPMENT("Shipment awaiting"),
    SHIPPED("Shipped"),
    DELIVERED("Delivered");

    private String name;

    OrderStatus (String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
