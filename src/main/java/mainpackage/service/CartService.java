package mainpackage.service;

import mainpackage.model.Cart;
import mainpackage.model.Clients;
import mainpackage.model.Items;

import java.util.List;

public interface CartService {
    List<Items> getGuestShoppingCart();

    List<Items> getUsersShoppingCart(String userLogin);

    void addItemToGuestCart (Integer itemId, int guestCartId);

    Cart createGuestCart ();

    Cart createUserCart (Clients client);
}
