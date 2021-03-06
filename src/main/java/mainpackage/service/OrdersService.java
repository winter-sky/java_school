package mainpackage.service;

import mainpackage.model.ClientAddresses;
import mainpackage.model.Items;
import mainpackage.model.Orders;
import mainpackage.type.DeliveryMethod;
import mainpackage.type.OrderStatus;
import mainpackage.type.PaymentMethod;

import java.sql.Timestamp;
import java.util.List;

public interface OrdersService {

    void selectOrderAddress(int addressId, int orderId);

    List<Orders> getUserOrders(String userLogin);

    void selectPaymentMethod(PaymentMethod paymentMethod, String userLogin);

    void selectDeliveryMethod (DeliveryMethod deliveryMethod, String userLogin);

    void payForTheOrder(String userLogin);

    void  addNewOrder(String userLogin, List<Items> itemsFromCart);

    List<Items> getUserCurrentOrder (String userLogin);

    Orders getCurrentOrder(String userLogin);

    List<Orders> getOrders (String userLogin);

    List<Orders> getAllOrders ();

    List<Orders> getOrdersAfter (Timestamp ts);

    List<Orders> showAllOrdersForAdmin();

    Orders findOrderById(int orderId);

    void selectOrderStatus(OrderStatus orderStatus, int orderId);
}
