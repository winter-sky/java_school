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
import java.util.Arrays;
import java.util.List;

@Repository("CartDAO")
public class CartDAOImpl implements CartDAO {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Cart getCart(int cardId) {
        Query query = em.createQuery("from Cart where cart_id=:cardId");

        return (Cart) query.setParameter("cardId", cardId).getSingleResult();
    }

    @Override
    public Cart createGuestCart() {//add new guest Cart to database
        Cart guestCart = new Cart();

        em.persist(guestCart);

        return guestCart;
    }

    @Override
    public Cart createUserCart(Clients client) {//add new user Cart to database
        Cart userCart = new Cart();

        userCart.setClient(client);

        em.persist(userCart);

        return userCart;
    }
}
