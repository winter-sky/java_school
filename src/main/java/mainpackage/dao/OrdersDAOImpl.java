package mainpackage.dao;

import mainpackage.model.Clients;
import mainpackage.model.Logins;
import mainpackage.model.Orders;
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
}
