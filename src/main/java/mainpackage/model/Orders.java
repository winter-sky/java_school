package mainpackage.model;

import mainpackage.type.DeliveryMethod;
import mainpackage.type.OrderStatus;
import mainpackage.type.PaymentMethod;
import mainpackage.type.PaymentState;

import javax.persistence.*;
import java.util.List;

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
    private PaymentState paymentStatus;

    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name="orders_client_address")
    ClientAddresses clientAddresses;

    @ManyToOne
    @JoinColumn(name="orders_client")
    Clients client;

    @ManyToMany
    @JoinTable(
            name = "order_items",
            joinColumns = { @JoinColumn(name = "orders") },
            inverseJoinColumns = { @JoinColumn(name = "items") }
    )
    List<Items> items;

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

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }
}