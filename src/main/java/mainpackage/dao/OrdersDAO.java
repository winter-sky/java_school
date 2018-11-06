package mainpackage.dao;

import mainpackage.model.Orders;
import mainpackage.type.PaymentMethod;

import java.util.List;

public interface OrdersDAO {
    void selectPaymentMethod(PaymentMethod paymentMethod, String userLogin);//set payment method in user order

    List<Orders> getUserOrders(String userLogin);
}
