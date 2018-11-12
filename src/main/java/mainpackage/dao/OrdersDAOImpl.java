package mainpackage.dao;

import mainpackage.model.*;
import mainpackage.type.DeliveryMethod;
import mainpackage.type.OrderStatus;
import mainpackage.type.PaymentMethod;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
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
    public void payForTheOrder(String userLogin) {
        Query query = em.createQuery("from Logins");

        List<Logins> logins = query.getResultList();

        Clients client = new Clients();

        for (Logins l : logins) {
            if ((l.getLogin()).equals(userLogin)) {
                client = l.getClient();
            }
        }

        List<Orders> userOrders = client.getOrders();

        Orders userOrder = new Orders();

        for (Orders o : userOrders) {
            if (o.getPaymentStatus().equals(AWAITING_PAYMENT)) {
                userOrder = o;
            }
        }

        userOrder.setPaymentStatus(PAID);
        userOrder.setOrderStatus(AWAITING_SHIPMENT);
    }

    @Override
    public void selectPaymentMethod(PaymentMethod paymentMethod, String userLogin) {//need to receive order id
        Query query = em.createQuery("from Logins");

        List<Logins> logins = query.getResultList();

        Clients client = new Clients();

        for (Logins l : logins) {
            if ((l.getLogin()).equals(userLogin)) {
                client = l.getClient();
            }
        }

        List<Orders> userOrders = client.getOrders();//is it more properly get the last order? must be improved

        Orders userOrder = new Orders();

        for (Orders o : userOrders) {
            if (o.getPaymentStatus().equals(AWAITING_PAYMENT)) {
                userOrder = o;
            }
        }

        userOrder.setPaymentMethod(paymentMethod);
    }

    @Override
    public void selectDeliveryMethod(DeliveryMethod deliveryMethod, String userLogin) {
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

        Orders userOrder = new Orders();

        for (Orders o : userOrders) {
            if (o.getPaymentStatus().equals(AWAITING_PAYMENT)) {
                userOrder = o;
            }
        }

        userOrder.setDeliveryMethod(deliveryMethod);
    }

    @Override
    public List<Orders> getUserOrders(String userLogin) {//show all orders by client login
        CriteriaBuilder builder = em.getCriteriaBuilder();

        CriteriaQuery<Orders> query = builder.createQuery(Orders.class);

        Root<Orders> o = query.from(Orders.class);

        Predicate predicate = builder.conjunction();

        predicate = builder.and(predicate, builder.like(o.get("client").get("login").get("login"), userLogin));

        query.where(predicate);
        query.orderBy(builder.desc(o.get("orderDate")));

        TypedQuery<Orders> typedQuery = em.createQuery(query);

        return typedQuery.getResultList();
    }

    @Override
    public void addNewOrder(String userLogin, List<Items> itemsFromCart) {//must be renamed
        //find client
        Query query = em.createQuery("from Logins");

        List<Logins> logins = query.getResultList();

        Clients client = new Clients();

        for (Logins l : logins) {
            if ((l.getLogin()).equals(userLogin)) {
                client = l.getClient();
            }
        }

        //check whether some current user order with payment awaiting status exists in Orders table or not
        Query q = em.createQuery("from Orders");

        Orders newOrder = new Orders();

        List<Orders> allOrders = new ArrayList<>();

        if (q.getResultList().isEmpty()) {
            em.persist(newOrder);
        } else {
            allOrders = q.getResultList();
        }

        boolean check = false;

        for (Orders o : allOrders) {
            if (o.getClient().getClientId() == client.getClientId() && o.getPaymentStatus().equals(AWAITING_PAYMENT)) {
                newOrder = o;
                check = true;
            }
        }

        if (!check) {
            em.persist(newOrder);
        }

        newOrder.setClient(client);//??

        double orderPrice = newOrder.getOrderPrice();//for setting full order price

        for (Items item : itemsFromCart) {
            orderPrice += item.getPrice();
        }

        newOrder.setOrderPrice(orderPrice);

        ClientAddresses clientAddresses = client.getClientAddress();//??

        newOrder.setClientAddresses(clientAddresses);

        // TODO: something is wrong here.
        for (Items item : itemsFromCart) {
            OrderItems orderItems = new OrderItems();

            orderItems.setItem(item);
            orderItems.setOrder(newOrder);

            em.persist(orderItems);

            newOrder.addOrderItem(orderItems);//?
        }
    }

    @Override
    public List<Items> getUserCurrentOrder(String userLogin) {//get items from current user order
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

        if (client.getOrders() != null) {
            listUserOrders = client.getOrders();

            for (Orders o : listUserOrders) {
                if (o.getPaymentStatus().equals(AWAITING_PAYMENT))
                    currentOrder = o;
            }

            if (currentOrder.getOrderItems() != null) {//??
                for (OrderItems o : currentOrder.getOrderItems()) {
                    orderItems.add(o.getItem());
                }
            }
        }

        return orderItems;
    }

    @Override
    public List<Orders> showAllOrdersForAdmin() {
        CriteriaBuilder builder = em.getCriteriaBuilder();

        CriteriaQuery<Orders> q = builder.createQuery(Orders.class);

        Root<Orders> root = q.from(Orders.class);

        q.select(root);

        q.orderBy(builder.desc(root.get("orderDate")));

        TypedQuery<Orders> query = em.createQuery(q);

        List<Orders> listAllOrders = query.getResultList();

        return listAllOrders;
    }

    @Override
    public Orders findOrderById(int orderId) {
        Query query = em.createQuery("from Orders where order_id=:orderId");

        return (Orders) query.setParameter("orderId", orderId).getSingleResult();
    }

    @Override
    public void selectOrderStatus(OrderStatus orderStatus, int orderId) {
        Orders order = findOrderById(orderId);

        order.setOrderStatus(orderStatus);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Orders> getAllOrders() {
        return em.createQuery("from Orders").getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Orders> getOrdersAfter(Timestamp ts) {
        Query query = em.createQuery("from Orders WHERE order_date >= :ts");

        return query.setParameter("ts", ts).getResultList();
    }
}
