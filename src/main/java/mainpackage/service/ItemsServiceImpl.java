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

}
