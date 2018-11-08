package mainpackage.dao;

import mainpackage.model.Cart;
import mainpackage.model.Clients;
import mainpackage.model.Items;
import mainpackage.model.Orders;

import java.util.List;

public interface CartDAO {
    List<Items> getGuestShoppingCart();//isn't used, should be deleted

    List<Items> getUsersShoppingCart(String userLogin);

    void addItemToGuestCart (int itemId, int guestCartId);

    Cart createGuestCart ();

    Cart createUserCart(Clients client);


}
