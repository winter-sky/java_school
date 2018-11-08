package mainpackage.service;

import mainpackage.dao.OrdersDAO;
import mainpackage.model.Items;
import mainpackage.model.Orders;
import mainpackage.type.PaymentMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Override
    @Transactional
    public List<Items> getUserCurrentOrder (String userLogin){return this.ordersDAO.getUserCurrentOrder(userLogin);}
}
