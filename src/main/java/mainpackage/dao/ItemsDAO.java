package mainpackage.dao;

import mainpackage.model.Items;
import mainpackage.model.Logins;

import java.security.Principal;
import java.util.List;

public interface ItemsDAO {
    List<Items> listItems();

    Items findItemById(int itemId);

    void addNewItem( int categoryId, String author,String format,String language,String itemName, double price,double weight,
                     String volume,int availableCount,String pic);

    List<Items> showListAllItems();

    void updateItem(int itemId,String itemName,double price,double weight,String volume,int availableCount,
                    String pic, int categoryId,String author,String format,String language);

   // void removeItem(int itemId);
}
