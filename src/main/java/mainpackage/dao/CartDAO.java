package mainpackage.dao;

import mainpackage.model.Cart;
import mainpackage.model.Clients;
import mainpackage.model.Items;
import mainpackage.model.Orders;

import java.util.List;

public interface CartDAO {
    Cart getCart(int cardId);

    Cart createGuestCart();

    Cart createUserCart(Clients client);


}
