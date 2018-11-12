package mainpackage.model;

import mainpackage.type.DeliveryMethod;
import mainpackage.type.OrderStatus;
import mainpackage.type.PaymentMethod;
import mainpackage.type.PaymentState;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static mainpackage.type.OrderStatus.PAYMENT_AWAITING;
import static mainpackage.type.PaymentState.AWAITING_PAYMENT;

@Entity
@Table(name="orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;

    @Column(name = "payment_method")
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Column(name = "delivery_method")
    @Enumerated(EnumType.STRING)
    private DeliveryMethod deliveryMethod;

    @Column(name = "payment_state")
    @Enumerated(EnumType.STRING)
    private PaymentState paymentStatus=AWAITING_PAYMENT;

    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus=PAYMENT_AWAITING;

    @Column(name = "order_price")
    private double orderPrice;

    @Column(name = "order_date")
    private Timestamp orderDate;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "order")
    List<OrderItems> orderItems = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="orders_client_address")
    ClientAddresses clientAddresses;

    @ManyToOne
    @JoinColumn(name="orders_client")
    Clients client;

    @Override
    public String toString() {
        return "Order [id=" + orderId + ", Order date = " + orderDate + ", Client = " + client + ", Client address "+
                clientAddresses + "Full order price  " + orderPrice + "]";
    }

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

    public ClientAddresses getClientAddresses() {
        return clientAddresses;
    }

    public void setClientAddresses(ClientAddresses clientAddresses) {
        this.clientAddresses = clientAddresses;
    }

    public Clients getClient() {
        return client;
    }

    public void setClient(Clients client) {
        this.client = client;
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

    public List<OrderItems> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItems> orderItems) {
        this.orderItems = orderItems;
    }

    public void addOrderItem(OrderItems orderItems) {
        this.orderItems.add(orderItems);
    }

    public boolean isPaymentAwaiting(){
        return orderStatus==PAYMENT_AWAITING;
    }

    public boolean getIsPaymentAwaiting() {
        return orderStatus==PAYMENT_AWAITING;
    }

    public void setIsPaymentAwaiting(boolean isPaymentAwaiting) {

    }
}
