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
    public void payForTheOrder(Orders o) {
        o.setPaymentStatus(PAID);
        o.setOrderStatus(AWAITING_SHIPMENT);
    }

    @Override
    public void selectPaymentMethod(Orders o, PaymentMethod paymentMethod) {
        o.setPaymentMethod(paymentMethod);
    }

    @Override
    public void selectDeliveryMethod(Orders o, DeliveryMethod deliveryMethod) {
        o.setDeliveryMethod(deliveryMethod);
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
    public void addNewOrder(Clients client, List<Items> itemsFromCart) {//must be renamed
        //check whether some current user order with payment awaiting status exists in Orders table or not
        Query q = em.createQuery("from Orders WHERE orders_client = :clientId");

        List<Orders> clientOrds = (List<Orders>)q.setParameter("clientId", client.getClientId()).getResultList();

        Orders newOrder = new Orders();

        boolean found = false;

        for (Orders o : clientOrds) {
            if (o.getPaymentStatus().equals(AWAITING_PAYMENT)) {
                newOrder = o;

                found = true;

                break;
            }
        }

        if (!found) {
            newOrder.setClient(client);

            em.persist(newOrder);
        }

        double orderPrice = newOrder.getOrderPrice();//for setting full order price

        for (Items item : itemsFromCart) {
            orderPrice += item.getPrice();
        }

        newOrder.setOrderPrice(orderPrice);

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
    public List<Orders> showAllOrdersForAdmin() {
        CriteriaBuilder builder = em.getCriteriaBuilder();

        CriteriaQuery<Orders> q = builder.createQuery(Orders.class);

        Root<Orders> root = q.from(Orders.class);

        q.select(root);

        q.orderBy(builder.desc(root.get("orderDate")));

        TypedQuery<Orders> query = em.createQuery(q);

        return query.getResultList();
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
