package mainpackage.dao;

import mainpackage.model.*;
import mainpackage.type.DeliveryMethod;
import mainpackage.type.OrderStatus;
import mainpackage.type.PaymentMethod;

import java.sql.Timestamp;
import java.util.List;

public interface OrdersDAO {

    void selectPaymentMethod(PaymentMethod paymentMethod, String userLogin);//set payment method in user order

    void selectDeliveryMethod (DeliveryMethod deliveryMethod, String userLogin);

    List<Orders> getUserOrders(String userLogin);//show all user orders

    void  addNewOrder(String userLogin, List<Items> itemsFromCart);

    List<Items> getUserCurrentOrder (String userLogin);//find client order with payment awaiting status

    Orders getCurrentOrder(String user);

    void payForTheOrder(String userLogin);

    List<Orders> getOrders (String userLogin);//find client not delivered orders

    List<Orders> getAllOrders ();//find client not delivered orders

    List<Orders> getOrdersAfter (Timestamp ts);

    List<Orders> showAllOrdersForAdmin();

    Orders findOrderById(int orderId);

    void selectOrderStatus(OrderStatus orderStatus, int orderId);

    void selectOrderAddress(int addressId,int orderId);
}
