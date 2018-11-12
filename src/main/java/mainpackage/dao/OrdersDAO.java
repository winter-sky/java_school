package mainpackage.dao;

import mainpackage.model.*;
import mainpackage.type.DeliveryMethod;
import mainpackage.type.OrderStatus;
import mainpackage.type.PaymentMethod;

import java.sql.Timestamp;
import java.util.List;

public interface OrdersDAO {
    void selectPaymentMethod(Orders o, PaymentMethod paymentMethod);//set payment method in user order

    void selectDeliveryMethod(Orders o, DeliveryMethod deliveryMethod);

    List<Orders> getUserOrders(String userLogin);//show all user orders

    void  addNewOrder(Clients client, List<Items> itemsFromCart);

    void payForTheOrder(Orders o);

    List<Orders> getAllOrders ();//find client not delivered orders

    List<Orders> getOrdersAfter (Timestamp ts);

    List<Orders> showAllOrdersForAdmin();

    Orders findOrderById(int orderId);

    void selectOrderStatus(OrderStatus orderStatus, int orderId);
}
