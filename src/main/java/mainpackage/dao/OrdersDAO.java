package mainpackage.dao;

import mainpackage.model.Items;
import mainpackage.model.OrderItems;
import mainpackage.model.Orders;
import mainpackage.model.Params;
import mainpackage.type.DeliveryMethod;
import mainpackage.type.OrderStatus;
import mainpackage.type.PaymentMethod;

import java.util.List;

public interface OrdersDAO {
    double showMonthProceeds();

    void selectPaymentMethod(PaymentMethod paymentMethod, String userLogin);//set payment method in user order

    void selectDeliveryMethod (DeliveryMethod deliveryMethod, String userLogin);

    List<Orders> getUserOrders(String userLogin);//is not used

    void  addNewOrder(String userLogin, List<Items> itemsFromCart);

    List<Items> getUserCurrentOrder (String userLogin);//find client order with payment awaiting status

    Orders getCurrentOrder(String user);

    void payForTheOrder(String userLogin);

    List<Orders> getOrders (String userLogin);//find client not delivered orders

    List<Orders> showAllOrdersForAdmin();

    Orders findOrderById(int orderId);

    void selectOrderStatus(OrderStatus orderStatus, int orderId);
}
