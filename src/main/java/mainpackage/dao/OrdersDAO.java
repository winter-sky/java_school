package mainpackage.dao;

import mainpackage.type.PaymentMethod;

public interface OrdersDAO {
    void selectPaymentMethod(PaymentMethod paymentMethod, String userLogin);
}
