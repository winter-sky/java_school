package mainpackage.model;

import javax.persistence.*;

@Entity
@Table(name="order_items")
public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_items_id")
    private int orderItemsId;

    @Column(name="item_quantity")
    private int itemQuantity;

    @ManyToOne
    @JoinColumn(name="orders")
    private Orders order;

    @ManyToOne
    @JoinColumn(name="items")
    private Items item;

    public int getOrderItemsId() {
        return orderItemsId;
    }

    public void setOrderItemsId(int orderItemsId) {
        this.orderItemsId = orderItemsId;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public Items getItem() {
        return item;
    }

    public void setItem(Items item) {
        this.item = item;
    }
}
