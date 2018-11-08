package mainpackage.dao;

import mainpackage.model.Items;
import mainpackage.model.Logins;

import java.security.Principal;
import java.util.List;

public interface ItemsDAO {
    List<Items> listItems();

    Items findItemById(int itemId);

//    List<Items> guestShoppingCart();//with Orders table using
//
//    List<Items> getUsersShoppingCart(String userLogin);//with Orders table using

    void addNewItem( int categoryId, String author,String format,String language,String itemName, double price,double weight,
                     String volume,int availableCount,String pic);
}
