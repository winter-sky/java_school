package mainpackage.service;

import mainpackage.dao.OrdersDAO;
import mainpackage.model.ClientAddresses;
import mainpackage.model.Items;
import mainpackage.model.Orders;
import mainpackage.model.*;
import mainpackage.type.DeliveryMethod;
import mainpackage.type.OrderStatus;
import mainpackage.type.PaymentMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static mainpackage.type.OrderStatus.DELIVERED;
import static mainpackage.type.PaymentState.AWAITING_PAYMENT;

@Service("OrdersService")
@Transactional
public class OrdersServiceImpl implements OrdersService {
    private OrdersDAO ordersDAO;

    private ClientsService clientsService;

    @Autowired
    public void setOrdersDAO(OrdersDAO ordersDAO) {
        this.ordersDAO = ordersDAO;
    }

    @Autowired
    public void setClientsService(ClientsService clientsService) {
        this.clientsService = clientsService;
    }

    @Override
    public void selectOrderAddress(int addressId, int orderId){
        this.ordersDAO.selectOrderAddress(addressId,orderId);
    }

    @Override
    public void selectPaymentMethod(PaymentMethod paymentMethod, String userLogin){
        Clients client = clientsService.findClientByLogin(userLogin);

        for (Orders o : client.getOrders()) {
            if (o.getPaymentStatus().equals(AWAITING_PAYMENT)) {
                ordersDAO.selectPaymentMethod(o, paymentMethod);
            }
        }
    }

    @Override
    public List<Orders> getUserOrders(String userLogin){
        return this.ordersDAO.getUserOrders(userLogin);
    }

    @Override
    public void  addNewOrder(String userLogin, List<Items> itemsFromCart){
        Clients client = clientsService.findClientByLogin(userLogin);

        this.ordersDAO.addNewOrder(client,itemsFromCart);
    }

    @Override
    public List<Items> getUserCurrentOrder (String userLogin){
        Orders o = getCurrentOrder(userLogin);

        return o!= null ? o.getOrderItems().stream().map(OrderItems::getItem).collect(Collectors.toList()) : null;
    }

    @Override
    public Orders getCurrentOrder(String userLogin){
        Clients client = clientsService.findClientByLogin(userLogin);

        // Assume only one order for client awaiting payment.
        if (client.getOrders() != null) {
            for (Orders o : client.getOrders()) {
                if (o.getPaymentStatus().equals(AWAITING_PAYMENT))
                    return o;
            }
        }

        return null;
    }

    @Override
    public void selectDeliveryMethod(DeliveryMethod deliveryMethod, String userLogin){
        Clients client = clientsService.findClientByLogin(userLogin);

        for (Orders o : client.getOrders()) {
            if (o.getPaymentStatus().equals(AWAITING_PAYMENT)) {
                ordersDAO.selectDeliveryMethod(o, deliveryMethod);
            }
        }
    }

    @Override
    public void payForTheOrder(String userLogin){
        Clients client = clientsService.findClientByLogin(userLogin);

        for (Orders o : client.getOrders()) {
            if (o.getPaymentStatus().equals(AWAITING_PAYMENT)) {
                ordersDAO.payForTheOrder(o);
            }
        }
    }

    @Override
    public List<Orders> getOrders (String userLogin){
        Clients client = clientsService.findClientByLogin(userLogin);

        return client.getOrders() != null ? client.getOrders().stream().filter(
            o -> !o.getOrderStatus().equals(DELIVERED)).collect(Collectors.toList()) : new ArrayList<>();
    }

    @Override
    @Transactional
    public List<Orders> getAllOrders() {
        return ordersDAO.getAllOrders();
    }

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

    @Override
    public List<Orders> getOrdersAfter(Timestamp ts) {
        return ordersDAO.getOrdersAfter(ts);
    }
}
