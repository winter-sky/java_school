package mainpackage.service;

import mainpackage.dto.ItemDTO;
import mainpackage.model.Items;

import java.security.Principal;
import java.util.List;

public interface ItemsService {
    List<Items> listItems();

//    List<Items> guestShoppingCart();
//
//    List<Items> userShoppingCart(String userLogin);

    Items findItemById(Integer itemId);

    void saveItem(ItemDTO item);
}
