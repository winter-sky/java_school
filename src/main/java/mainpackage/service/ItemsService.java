package mainpackage.service;

import mainpackage.dto.ItemDTO;
import mainpackage.model.Items;

import java.security.Principal;
import java.util.List;

public interface ItemsService {
    List<ItemDTO> listItems();

//    List<Items> guestShoppingCart();
//
//    List<Items> userShoppingCart(String userLogin);

    Items findItemById(int itemId);

    void saveItem(ItemDTO item);

    void addNewItem( int categoryId, String author,String format,String language,String itemName, double price,double weight,
                     String volume,int availableCount,String pic);
}
