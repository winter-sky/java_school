package mainpackage.dao;

import mainpackage.model.*;
import mainpackage.type.DeliveryMethod;
import mainpackage.type.OrderStatus;
import mainpackage.type.PaymentMethod;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static mainpackage.type.OrderStatus.AWAITING_SHIPMENT;
import static mainpackage.type.OrderStatus.DELIVERED;
import static mainpackage.type.PaymentState.AWAITING_PAYMENT;
import static mainpackage.type.PaymentState.PAID;

@Repository("OrdersDAO")
public class OrdersDAOImpl implements OrdersDAO {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void payForTheOrder(String userLogin){
        Query query = em.createQuery("from Logins");
        List<Logins> logins = query.getResultList();

        Clients client = new Clients();

        for (Logins l : logins) {
            if ((l.getLogin()).equals(userLogin)) {
                System.out.println(l.getLogin());
                client = l.getClient();
            }
        }

        List<Orders> userOrders = client.getOrders();//is it more properly get the last order? must be improved
        System.out.println("Orders client: " + client.getOrders());
        Orders userOrder = new Orders();
        for(Orders o:userOrders){
            if(o.getPaymentStatus().equals(AWAITING_PAYMENT)){
                System.out.println("Must be found: " + o);
                userOrder=o;
            }
        }
        userOrder.setPaymentStatus(PAID);
        userOrder.setOrderStatus(AWAITING_SHIPMENT);
    }

    @Override
    public void selectPaymentMethod(PaymentMethod paymentMethod, String userLogin){//need to receive order id
        Query query = em.createQuery("from Logins");
        List<Logins> logins = query.getResultList();

        Clients client = new Clients();

        for (Logins l : logins) {
            if ((l.getLogin()).equals(userLogin)) {
                System.out.println(l.getLogin());
                client = l.getClient();
            }
        }

        List<Orders> userOrders = client.getOrders();//is it more properly get the last order? must be improved
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
    public void selectDeliveryMethod (DeliveryMethod deliveryMethod, String userLogin){
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

        List<Orders> userOrders = client.getOrders();//is it more properly get the last order? must be improved
        System.out.println("Orders client: " + client.getOrders());
        Orders userOrder = new Orders();
        for(Orders o:userOrders){
            if(o.getPaymentStatus().equals(AWAITING_PAYMENT)){
                System.out.println("Must be found: " + o);
                userOrder=o;
            }
        }
        userOrder.setDeliveryMethod(deliveryMethod);
    }

    @Override
    public List<Orders> getUserOrders(String userLogin){//what is it?
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
        listUserOrders = client.getOrders();
        return listUserOrders;
    }

    @Override
    public void  addNewOrder(String userLogin, int itemId){//must be renamed
        Query query = em.createQuery("from Logins");
        List<Logins> logins = query.getResultList();
        Clients client = new Clients();
        for (Logins l : logins) {
            if ((l.getLogin()).equals(userLogin)) {
                System.out.println(l.getLogin());
                client = l.getClient();
            }
        }
        //check whether some current user order with payment awaiting status exists in Orders table or not
        Query q = em.createQuery("from Orders");
        Orders newOrder=new Orders();
        List<Orders> allOrders = new ArrayList<>();
        if(q.getResultList().isEmpty()) {
            em.persist(newOrder);
        }
        else
         allOrders= q.getResultList();

        boolean check = false;
        for(Orders o:allOrders){
            if(o.getClient().getClientId()==client.getClientId()&&o.getPaymentStatus().equals(AWAITING_PAYMENT)){
                newOrder=o;
                check=true;
            }
        }
        if(!check)
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

    @Override
    public List<Items> getUserCurrentOrder (String userLogin){//get items from current user order
        //find user
        Query query = em.createQuery("from Logins");
        List<Logins> logins = query.getResultList();
        Clients client = new Clients();
        for (Logins l : logins) {
            if ((l.getLogin()).equals(userLogin)) {
                System.out.println(l.getLogin());
                client = l.getClient();
            }
        }

        Orders currentOrder = new Orders();
        List<Orders> listUserOrders = new ArrayList<>();
        List<Items> orderItems = new ArrayList<>();
      if(client.getOrders()!=null){
          listUserOrders =client.getOrders();
          for(Orders o:listUserOrders){
             if(o.getPaymentStatus().equals(AWAITING_PAYMENT))
                 currentOrder=o;
             //TODO
          }
          if(currentOrder.getOrderItems()!=null) {//??
              for (OrderItems o : currentOrder.getOrderItems()) {
                  orderItems.add(o.getItem());
              }
          }
      }
        return orderItems;
    }

    @Override
    public Orders getCurrentOrder(String userLogin){
        Query query = em.createQuery("from Logins where login = :login").setParameter("login", userLogin);
        Logins login =(Logins) query.getSingleResult();
//        List<Logins> logins = query.getFirstResult();
        Clients client = new Clients();
        client = login.getClient();
//        for (Logins l : logins) {
//            if ((l.getLogin()).equals(userLogin)) {
//                System.out.println(l.getLogin());
//                client = l.getClient();
//            }
//        }

        Orders currentOrder = new Orders();
        List<Orders> listUserOrders = new ArrayList<>();
        List<Items> orderItems = new ArrayList<>();
        if(client.getOrders()!=null) {
            listUserOrders = client.getOrders();
            for (Orders o : listUserOrders) {
                if (o.getPaymentStatus().equals(AWAITING_PAYMENT))
                    currentOrder = o;
            }
        }
            return currentOrder;
    }

    @Override
    public List<Orders> getOrders (String userLogin){
        Query query = em.createQuery("from Logins");
        List<Logins> logins = query.getResultList();
        Clients client = new Clients();
        for (Logins l : logins) {
            if ((l.getLogin()).equals(userLogin)) {
                System.out.println(l.getLogin());
                client = l.getClient();
            }
        }

        List<Orders> listOrders = new ArrayList<>();
        if(client.getOrders()!=null) {
            for (Orders o : client.getOrders()) {
                if (!o.getOrderStatus().equals(DELIVERED))
                    listOrders.add(o);
            }
        }
        return listOrders;
    }

    @Override
    public List<Orders> showAllOrdersForAdmin(){
        Query query = em.createQuery("from Orders");
        List<Orders> listAllOrders = query.getResultList();
        return listAllOrders;
    }

    @Override
    public Orders findOrderById(int orderId){
        Query query = em.createQuery("from Orders where order_id=:orderId");
        return  (Orders) query.setParameter("orderId", orderId).getSingleResult();
    }

    @Override
    public void selectOrderStatus(OrderStatus orderStatus, int orderId){
        Orders order =findOrderById(orderId);
        order.setOrderStatus(orderStatus);
    }
//    @Override
//    public OrderItems getOrderItemsById (int orderItemsId){
//        Query query = em.createQuery("from OrderItems where order_items_id=:orderItemsId");
//        OrderItems orderItems = (OrderItems) query.setParameter("orderItemsId", orderItemsId).getSingleResult();
//        return orderItems;
//    }
//
//    @Override
//    public void updateOrderItemQuantity (OrderItems orderItem){
//        OrderItems orderItemsDb = (OrderItems) em.find(OrderItems.class, orderItem.getOrderItemsId());
//        orderItemsDb.setItemQuantity(orderItem.getItemQuantity());
//    }


    @Override
    @SuppressWarnings("unchecked")
    public List<Orders> getAllOrders() {
        return em.createQuery("from Orders").getResultList();
    }

    @Override
    public List<Orders> getOrdersAfter(Timestamp ts) {
        Query query = em.createQuery("from Orders WHERE order_date >= :ts");

        return query.setParameter("ts", ts).getResultList();
    }
}
