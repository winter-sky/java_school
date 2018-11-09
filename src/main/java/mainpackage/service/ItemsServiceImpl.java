package mainpackage.service;

import mainpackage.dao.ItemsDAO;
import mainpackage.dto.ItemDTO;
import mainpackage.model.Items;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service("ItemsService")
public class ItemsServiceImpl implements ItemsService {

    @Autowired//???
    @Qualifier("ItemsDAO")
    private ItemsDAO itemsDAO;

    @Autowired
    private MappingService mappingService;

    @Override
    @Transactional
    public List<ItemDTO> listItems() {
        List<Items> entitiesList = this.itemsDAO.listItems();
        List<ItemDTO> itemDTOList = entitiesList.stream().map(it -> mappingService.itemEntityToItemDTO(it)).collect(Collectors.toList());
        return itemDTOList;
    }

//    @Override
//    @Transactional
//    public List<Items> guestShoppingCart(){return  this.itemsDAO.guestShoppingCart();}
//
//    @Override
//    @Transactional
//    public List<Items> userShoppingCart(String userLogin){return  this.itemsDAO.getUsersShoppingCart(userLogin);}

    @Override
    @Transactional
    public Items findItemById(int itemId) {
        return this.itemsDAO.findItemById(itemId);
    }

    @Override
    public void saveItem(ItemDTO item) {
        Items itemEntity = mappingService.itemDTOtoItemEntity(item);
//        if (itemEntity.getItemId() != null) {
//            // Item already exists in DB since it has ID - so update item
//        } else {
//            // We need to create new item - insert new item to table
//        }
    }

    @Override
    @Transactional
    public void addNewItem( int categoryId, String author,String format,String language,String itemName, double price,double weight,
                            String volume,int availableCount,String pic){
        this.itemsDAO.addNewItem(categoryId, author, format, language, itemName, price, weight, volume, availableCount, pic);
    }

    @Override
    @Transactional
    public List<Items> showListAllItems(){
        return this.itemsDAO.showListAllItems();
    }

    @Override
    @Transactional
    public void updateItem(int itemId,String itemName,double price,double weight,String volume,int availableCount,
                           String pic, int categoryId,String author,String format,String language){
        this.itemsDAO.updateItem(itemId, itemName, price, weight, volume, availableCount, pic, categoryId, author, format, language);
    }

    @Override
    public List<Items> findItemsByIds(Integer[] itemId) {
        return itemsDAO.findItemsByIds(itemId);
    }
}
