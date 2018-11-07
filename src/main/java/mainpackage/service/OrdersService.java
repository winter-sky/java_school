package mainpackage.service;

import mainpackage.model.Items;
import mainpackage.model.Orders;
import mainpackage.type.PaymentMethod;

public interface OrdersService {
    void selectPaymentMethod(PaymentMethod paymentMethod, String userLogin);

    void  addNewOrder(String userLogin, Integer itemId);

//    Orders getUserCurrentOrder (String userLogin);
}
