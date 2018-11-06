package mainpackage.service;

import mainpackage.type.PaymentMethod;

public interface OrdersService {
    void selectPaymentMethod(PaymentMethod paymentMethod, String userLogin);
}
