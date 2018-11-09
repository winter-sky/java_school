package mainpackage.dao;

import mainpackage.model.Cart;
import mainpackage.model.Clients;
import mainpackage.model.Items;
import mainpackage.model.Logins;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository("CartDAO")
public class CartDAOImpl implements CartDAO {
    @PersistenceContext
    private EntityManager em;

    @Override
    @Deprecated
    public List<Items> getGuestShoppingCart() {//isn't used
        Query query = em.createQuery("from  Cart");
        List<Cart> listOrders = query.getResultList();
        List<Items> cartItems = new ArrayList<>();
        for (Cart o : listOrders) {
            if (o.getClient() == null)
                cartItems.addAll(o.getItems());
        }
        return cartItems;
    }

    @Override
    public List<Items> getUsersShoppingCart(String userLogin) {
        Query query = em.createQuery("from Logins");
        List<Logins> logins = query.getResultList();

        Clients client = new Clients();

        for (Logins l : logins) {
            {
                    if ((l.getLogin()).equals(userLogin)) {
                        System.out.println(l.getLogin());
                        client = l.getClient();
                        System.out.println(client.getFirstName());
                    }
            }
        }
        if(client.getCart()==null) {
            return null;
        }
            if(client.getCart().getItems()==null){
                return null;
            }

            return client.getCart().getItems();
    }

    @Override
    public void addItemToGuestCart (int itemId, int guestCartId){//add new item to DB guest Cart (if it exist in DB)

        Query query = em.createQuery("from Cart where cart_id=:guestCartId");
        Cart cart = (Cart)query.setParameter("guestCartId", guestCartId).getSingleResult();

        Query query2 = em.createQuery("from Items where item_id=:itemId");
        Items item = (Items)query2.setParameter("itemId", itemId).getSingleResult();

        if(cart.getItems()==null) {
            List<Items> items = new ArrayList<>();
            items.add(item);
            cart.setItems(items);
        }
        else cart.addItem(item);
    }

    @Override
    public Cart createGuestCart (){//add new guest Cart to database

        Cart guestCart = new Cart();
        em.persist(guestCart);

        return  guestCart;
    }

    @Override
    public Cart createUserCart (Clients client){//add new user Cart to database

        Cart userCart = new Cart();
        em.persist(userCart);
        userCart.setClient(client);

        return  userCart;
    }
}
