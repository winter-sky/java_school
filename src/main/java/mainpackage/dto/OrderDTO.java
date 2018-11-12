package mainpackage.dto;

import mainpackage.model.ClientAddresses;
import mainpackage.type.DeliveryMethod;
import mainpackage.type.OrderStatus;
import mainpackage.type.PaymentMethod;
import mainpackage.type.PaymentState;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static mainpackage.type.OrderStatus.PAYMENT_AWAITING;
import static mainpackage.type.PaymentState.AWAITING_PAYMENT;

public class OrderDTO {
    private int orderId;

    private PaymentMethod paymentMethod;

    private DeliveryMethod deliveryMethod;

    private PaymentState paymentStatus=AWAITING_PAYMENT;

    private OrderStatus orderStatus=PAYMENT_AWAITING;

    private double orderPrice;

    private Timestamp orderDate;

    List<OrderItemDTO> orderItems = new ArrayList<>();

    ClientAddressDTO clientAddresses;

    ClientDTO client;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public DeliveryMethod getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(DeliveryMethod deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public PaymentState getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentState paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public List<OrderItemDTO> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemDTO> orderItems) {
        this.orderItems = orderItems;
    }

    public ClientAddressDTO getClientAddresses() {
        return clientAddresses;
    }

    public void setClientAddresses(ClientAddressDTO clientAddresses) {
        this.clientAddresses = clientAddresses;
    }

    public ClientDTO getClient() {
        return client;
    }

    public void setClient(ClientDTO client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
            "orderId=" + orderId +
            ", paymentMethod=" + paymentMethod +
            ", deliveryMethod=" + deliveryMethod +
            ", paymentStatus=" + paymentStatus +
            ", orderStatus=" + orderStatus +
            ", orderPrice=" + orderPrice +
            ", orderDate=" + orderDate +
            ", orderItems=" + orderItems +
            ", clientAddresses=" + clientAddresses +
            ", client=" + client +
            '}';
    }
}
