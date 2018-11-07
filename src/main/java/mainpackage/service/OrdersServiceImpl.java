package mainpackage.service;

import mainpackage.dao.OrdersDAO;
import mainpackage.model.Items;
import mainpackage.type.PaymentMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("OrdersService")
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    @Qualifier("OrdersDAO")
    private OrdersDAO ordersDAO;

    @Override
    @Transactional
    public void selectPaymentMethod(PaymentMethod paymentMethod, String userLogin){
        this.ordersDAO.selectPaymentMethod(paymentMethod,userLogin);}


    @Override
    @Transactional
    public void  addNewOrder(String userLogin, int itemId){this.ordersDAO.addNewOrder(userLogin,itemId);}
}
