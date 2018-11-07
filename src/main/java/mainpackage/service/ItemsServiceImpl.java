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

@Service("ItemsService")
public class ItemsServiceImpl implements ItemsService {

    @Autowired//???
    @Qualifier("ItemsDAO")
    private ItemsDAO itemsDAO;

    @Autowired
    private MappingService mappingService;

    @Override
    @Transactional
    public List<Items> listItems(){return this.itemsDAO.listItems();}

//    @Override
//    @Transactional
//    public List<Items> guestShoppingCart(){return  this.itemsDAO.guestShoppingCart();}
//
//    @Override
//    @Transactional
//    public List<Items> userShoppingCart(String userLogin){return  this.itemsDAO.getUsersShoppingCart(userLogin);}

    @Override
    @Transactional
    public Items findItemById(Integer itemId){return this.itemsDAO.findItemById(itemId);}

    @Override
    public void saveItem(ItemDTO item) {
        Items itemEntity = mappingService.itemDTOtoItemEntity(item);
        if(itemEntity.getItemId() != null){
            // Item already exists in DB since it has ID - so update item
        } else {
            // We need to create new item - insert new item to table
        }
    }

}
