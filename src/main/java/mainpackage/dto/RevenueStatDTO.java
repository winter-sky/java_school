package mainpackage.dto;

import java.sql.Timestamp;

public class RevenueStatDTO {
    private Timestamp startDate;

    private int orders;

    private int items;

    private double revenue;

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public int getOrders() {
        return orders;
    }

    public void setOrders(int orders) {
        this.orders = orders;
    }

    public int getItems() {
        return items;
    }

    public void setItems(int items) {
        this.items = items;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    @Override
    public String toString() {
        return "RevenueStatDTO{" +
            "startDate=" + startDate +
            ", orders=" + orders +
            ", items=" + items +
            ", revenue=" + revenue +
            '}';
    }
}
