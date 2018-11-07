package mainpackage.dao;

import mainpackage.model.Items;
import mainpackage.model.Orders;
import mainpackage.type.PaymentMethod;

import java.util.List;

public interface OrdersDAO {
    void selectPaymentMethod(PaymentMethod paymentMethod, String userLogin);//set payment method in user order

    List<Orders> getUserOrders(String userLogin);

    void  addNewOrder(String userLogin, int itemId);
}
