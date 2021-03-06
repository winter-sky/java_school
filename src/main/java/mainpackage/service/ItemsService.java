package mainpackage.service;

import mainpackage.dto.ItemDTO;
import mainpackage.model.Items;

import java.security.Principal;
import java.util.List;

public interface ItemsService {
    List<ItemDTO> listItems();

    List<Items> searchItemsByString(String str);

    Items findItemById(int itemId);

    List<Items> findItemsByIds(List<Integer> itemIds);

    void saveItem(ItemDTO item);

    void addNewItem( int categoryId, String author,String format,String language,String itemName, double price,double weight,
                     String volume,int availableCount,String pic);

    List<Items> showListAllItems();

    void updateItem(int itemId,String itemName,double price,double weight,String volume,int availableCount,
                    String pic, int categoryId,String author,String format,String language);

}
