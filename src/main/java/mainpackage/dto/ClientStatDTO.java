package mainpackage.dto;

public class ClientStatDTO {
    private ClientDTO client;

    private int ordersCount;

    private int itemsCount;

    private double amount;

    public ClientDTO getClient() {
        return client;
    }

    public void setClient(ClientDTO client) {
        this.client = client;
    }

    public int getOrdersCount() {
        return ordersCount;
    }

    public void setOrdersCount(int ordersCount) {
        this.ordersCount = ordersCount;
    }

    public int getItemsCount() {
        return itemsCount;
    }

    public void setItemsCount(int itemsCount) {
        this.itemsCount = itemsCount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "ClientStatDTO{" +
            "client=" + client +
            ", ordersCount=" + ordersCount +
            ", itemsCount=" + itemsCount +
            ", amount=" + amount +
            '}';
    }
}
