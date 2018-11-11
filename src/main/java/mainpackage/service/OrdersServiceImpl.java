package mainpackage.service;

import mainpackage.dao.OrdersDAO;
import mainpackage.model.Items;
import mainpackage.model.Orders;
import mainpackage.type.DeliveryMethod;
import mainpackage.type.OrderStatus;
import mainpackage.type.PaymentMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("OrdersService")
@Transactional
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    @Qualifier("OrdersDAO")
    private OrdersDAO ordersDAO;

    @Override
    public void selectPaymentMethod(PaymentMethod paymentMethod, String userLogin){
        this.ordersDAO.selectPaymentMethod(paymentMethod,userLogin);
    }

    @Override
    public List<Orders> getUserOrders(String userLogin){
        return this.ordersDAO.getUserOrders(userLogin);
    }

    @Override
    public void  addNewOrder(String userLogin, List<Items> itemsFromCart){this.ordersDAO.addNewOrder(userLogin,itemsFromCart);}

    @Override
    public List<Items> getUserCurrentOrder (String userLogin){return this.ordersDAO.getUserCurrentOrder(userLogin);}

    @Override
    public Orders getCurrentOrder(String userLogin){return this.ordersDAO.getCurrentOrder(userLogin);}

    @Override
    public void selectDeliveryMethod (DeliveryMethod deliveryMethod, String userLogin){
        this.ordersDAO.selectDeliveryMethod(deliveryMethod,userLogin);
    }

    @Override
    public void payForTheOrder(String userLogin){this.ordersDAO.payForTheOrder(userLogin);}

    @Override
    public List<Orders> getOrders (String userLogin){return this.ordersDAO.getOrders(userLogin);}

    @Override
    public List<Orders> showAllOrdersForAdmin(){
        return this.ordersDAO.showAllOrdersForAdmin();
    }

    @Override
    public Orders findOrderById(int orderId){
        return this.ordersDAO.findOrderById(orderId);
    }

    @Override
    public void selectOrderStatus(OrderStatus orderStatus, int orderId){
        this.ordersDAO.selectOrderStatus(orderStatus, orderId);
    }
}
