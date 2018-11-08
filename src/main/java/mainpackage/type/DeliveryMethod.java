package mainpackage.type;

public enum DeliveryMethod {
    COURIER_DELIVERY("Courier delivery"),
    PICKUP("Pickup"),
    EMS("EMS");

    private String name;

    DeliveryMethod(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
