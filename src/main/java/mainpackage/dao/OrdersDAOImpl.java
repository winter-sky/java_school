package mainpackage.dao;

import mainpackage.model.*;
import mainpackage.type.PaymentMethod;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

import static mainpackage.type.PaymentState.AWAITING_PAYMENT;

@Repository("OrdersDAO")
public class OrdersDAOImpl implements OrdersDAO {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void selectPaymentMethod(PaymentMethod paymentMethod, String userLogin){
        Query query = em.createQuery("from Logins");
        List<Logins> logins = query.getResultList();

        Clients client = new Clients();

        for (Logins l : logins) {
            if ((l.getLogin()).equals(userLogin)) {
                System.out.println(l.getLogin());
                client = l.getClient();
            }
        }
        System.out.println("Client found: " + client.getClientId() +")"+" "+ client.getFirstName());

        List<Orders> userOrders = client.getOrders();//it will be properly get the last order?must be improved
        System.out.println("Orders client: " + client.getOrders());
        Orders userOrder = new Orders();
        for(Orders o:userOrders){
            if(o.getPaymentStatus().equals(AWAITING_PAYMENT)){
                System.out.println("Must be found: " + o);
              userOrder=o;
            }
        }
        userOrder.setPaymentMethod(paymentMethod);
    }

    @Override
    public List<Orders> getUserOrders(String userLogin){
        Query query = em.createQuery("from Logins");
        List<Logins> logins = query.getResultList();

        Clients client = new Clients();

        for (Logins l : logins) {
            if ((l.getLogin()).equals(userLogin)) {
                System.out.println(l.getLogin());
                client = l.getClient();
            }
        }

        List<Orders> listUserOrders = new ArrayList<>();
        if(client.getOrders()!=null)
        listUserOrders = client.getOrders();//need to add items to orders in DB for checking this method.
        return listUserOrders;
    }

    @Override
    public void  addNewOrder(String userLogin, int itemId){//must be renamed
        System.out.println("Are we in the fucking addNewOrder method in DAO?");
        //TODO check whether some current user order with payment awaiting status exists in Orders table or not

        Query query = em.createQuery("from Logins");
        List<Logins> logins = query.getResultList();

        Clients client = new Clients();

        for (Logins l : logins) {
            if ((l.getLogin()).equals(userLogin)) {
                System.out.println(l.getLogin());
                client = l.getClient();
            }
        }
        Orders newOrder = new Orders();
        em.persist(newOrder);
        newOrder.setClient(client);

        Query query2 = em.createQuery("from Items where item_id=:itemId");
        Items item = (Items)query2.setParameter("itemId", itemId).getSingleResult();

        System.out.println("Item found " + item.getItemName());

        double orderPrice=newOrder.getOrderPrice();//for setting full order price
        System.out.println("Order price  " + newOrder.getOrderPrice());
        orderPrice+=item.getPrice();
        System.out.println("Item price " + item.getPrice());
        System.out.println("Order price new  " + orderPrice);
        newOrder.setOrderPrice(orderPrice);

        ClientAddresses clientAddresses = client.getClientAddress();//for setting client adress
        newOrder.setClientAddresses(clientAddresses);

        OrderItems orderItems = new OrderItems();//for setting relation between orders and items
        orderItems.setOrder(newOrder);
        orderItems.setItem(item);
        em.persist(orderItems);
        List<OrderItems> listOrdersItems = new ArrayList<>();
        listOrdersItems.add(orderItems);
        newOrder.setOrderItems(listOrdersItems);

    }
}
