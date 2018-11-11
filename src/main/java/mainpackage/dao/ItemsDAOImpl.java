package mainpackage.dao;

import mainpackage.model.Categories;
import mainpackage.model.Items;
import mainpackage.model.Params;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository("ItemsDAO")
public class ItemsDAOImpl implements ItemsDAO {
    /** */
    private static final Logger log = LoggerFactory.getLogger(ItemsDAOImpl.class);

    @PersistenceContext
    private EntityManager em;

    @Override
    public void updateItem(
        int itemId,
        String itemName,
        double price,
        double weight,
        String volume,
        int availableCount,
        String pic,
        int categoryId,
        String author,
        String format,
        String language
    ){
        //find item with id received
        Query query1 = em.createQuery("from Items where item_id=:itemId");

        Items itemDB =  (Items) query1.setParameter("itemId", itemId).getSingleResult();

        //find category by id received
        Query query = em.createQuery("from Categories where category_id=:categoryId");

        Categories cat = (Categories) query.setParameter("categoryId", categoryId).getSingleResult();

        //get itemDB params
        Params paramDB = itemDB.getParams();
        paramDB.setAuthor(author);
        paramDB.setLanguage(language);
        paramDB.setFormat(format);

        itemDB.setCategory(cat);

        itemDB.setItemName(itemName);
        itemDB.setPrice(price);
        itemDB.setWeight(weight);
        itemDB.setVolume(volume);
        itemDB.setAvailableCount(availableCount);
        itemDB.setPic(pic);
    }

    @Override
    public List<Items> listItems() {
        return em.createQuery("SELECT i FROM Items i").getResultList();
    }

    @Override
    public Items findItemById(int itemId){
        Query query = em.createQuery("from Items where item_id=:itemId");

        return  (Items) query.setParameter("itemId", itemId).getSingleResult();
    }

    @Override
    public void addNewItem(
        int categoryId,
        String author,
        String format,
        String language,
        String itemName,
        double price,
        double weight,
        String volume,
        int availableCount,
        String pic
    ){
        Params newItemParam = new Params();

        newItemParam.setAuthor(author);
        newItemParam.setFormat(format);
        newItemParam.setLanguage(language);

        em.persist(newItemParam);

        Items newItem = new Items();
        newItem.setParams(newItemParam);

        Query query = em.createQuery("from Categories where category_id=:categoryId");

        Categories cat= (Categories) query.setParameter("categoryId", categoryId).getSingleResult();

        newItem.setCategory(cat);

        newItem.setItemName(itemName);
        newItem.setPrice(price);
        newItem.setWeight(weight);
        newItem.setVolume(volume);
        newItem.setAvailableCount(availableCount);
        newItem.setPic(pic);

        em.persist(newItem);
    }

    @Override
    public List<Items> showListAllItems(){
        Query query = em.createQuery("from Items");

        return query.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Items> findItemsByIds(List<Integer> itemIds) {
         log.debug("Requesting items by \nids: " + itemIds);

        Query query = em.createQuery("from Items where item_id IN (:itemIds)");

        return  (List<Items>) query.setParameter("itemIds", itemIds).getResultList();
    }
}
