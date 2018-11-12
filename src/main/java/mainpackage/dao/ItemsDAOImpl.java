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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.security.Principal;
import java.util.*;
import java.util.Arrays;
import java.util.List;

@Repository("ItemsDAO")
public class ItemsDAOImpl implements ItemsDAO {
    /** */
    private static final Logger log = LoggerFactory.getLogger(ItemsDAOImpl.class);

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Items> searchItemsByString(String str){

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Items> query = builder.createQuery(Items.class);
        Root<Items> r = query.from(Items.class);
        Predicate predicateCat = builder.conjunction();
        predicateCat = builder.and(predicateCat, builder.like(r.get("category").get("categoryName"), "%"+str+"%"));
        Predicate predicateName = builder.conjunction();
        predicateName = builder.and(predicateName, builder.like(r.get("itemName"), "%"+str+"%"));
        Predicate predicateAuthor = builder.conjunction();
        predicateAuthor = builder.and(predicateAuthor, builder.like(r.get("params").get("author"), "%"+str+"%"));
        Predicate predicateLanguage = builder.conjunction();
        predicateLanguage = builder.and(predicateLanguage, builder.like(r.get("params").get("language"), "%"+str+"%"));
        Predicate predicateFormat = builder.conjunction();
        predicateFormat = builder.and(predicateFormat, builder.like(r.get("params").get("format"), "%"+str+"%"));

        Predicate or = builder.or(predicateCat, predicateName, predicateAuthor,predicateLanguage,predicateFormat);
        query.where(or);

       return em.createQuery(query).getResultList();
    }

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
    @SuppressWarnings("unchecked")
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
    @SuppressWarnings("unchecked")
    public List<Items> showListAllItems(){
        Query query = em.createQuery("from Items");

        return query.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Items> findItemsByIds(List<Integer> itemIds) {
         log.debug("Requesting items by ids: " + itemIds);

        Query query = em.createQuery("from Items where item_id IN (:itemIds)");

        return  (List<Items>) query.setParameter("itemIds", itemIds).getResultList();
    }
}
