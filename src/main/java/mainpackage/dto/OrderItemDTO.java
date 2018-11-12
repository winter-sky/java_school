package mainpackage.dto;

public class OrderItemDTO {
    private int orderItemsId;

    private int itemQuantity=1;

    private OrderDTO order;

    private ItemDTO item;

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

    public OrderDTO getOrder() {
        return order;
    }

    public void setOrder(OrderDTO order) {
        this.order = order;
    }

    public ItemDTO getItem() {
        return item;
    }

    public void setItem(ItemDTO item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "OrderItemDTO{" +
            "orderItemsId=" + orderItemsId +
            ", itemQuantity=" + itemQuantity +
            ", order=" + order +
            ", item=" + item +
            '}';
    }
}
