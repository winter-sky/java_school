package mainpackage.dao;

import mainpackage.model.Cart;
import mainpackage.model.Clients;
import mainpackage.model.Items;

import java.util.List;

public interface CartDAO {
    List<Items> getGuestShoppingCart();//isn't used, should be deleted

    List<Items> getUsersShoppingCart(String userLogin);//must be improve
    // add in jsp checking for null

    void addItemToGuestCart (int itemId, int guestCartId);

    Cart createGuestCart ();

    Cart createUserCart(Clients client);
}