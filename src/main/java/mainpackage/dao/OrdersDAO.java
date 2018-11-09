package mainpackage.dao;

import mainpackage.model.Items;
import mainpackage.model.OrderItems;
import mainpackage.model.Orders;
import mainpackage.model.Params;
import mainpackage.type.DeliveryMethod;
import mainpackage.type.PaymentMethod;

import java.util.List;

public interface OrdersDAO {
    void selectPaymentMethod(PaymentMethod paymentMethod, String userLogin);//set payment method in user order

    void selectDeliveryMethod (DeliveryMethod deliveryMethod, String userLogin);

    List<Orders> getUserOrders(String userLogin);//is not used

    void  addNewOrder(String userLogin, int itemId);

    List<Items> getUserCurrentOrder (String userLogin);//find client order with payment awaiting status

   // Params getParamsItemFromOrderByLogin(String userLogin);

    Orders getCurrentOrder(String user);

    void payForTheOrder(String userLogin);

//    OrderItems getOrderItemsById (int orderItemsId);
//
//    void updateOrderItemQuantity (OrderItems orderItem);

    List<Orders> getOrders (String userLogin);//find client not delivered orders

    List<Orders> getAllOrders ();//find client not delivered orders
}
