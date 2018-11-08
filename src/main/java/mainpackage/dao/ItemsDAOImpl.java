package mainpackage.dao;

import mainpackage.model.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Repository("ItemsDAO")
public class ItemsDAOImpl implements ItemsDAO {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Items> listItems() {
        return em.createQuery("SELECT i FROM Items i").getResultList();
    }


    @Override
    public Items findItemById(int itemId){
        Query query = em.createQuery("from Items where item_id=:itemId");
        return  (Items) query.setParameter("itemId", itemId).getSingleResult();
    }

//    @Override
//    public List<Items> guestShoppingCart() {//with Order table  using
//        Query query = em.createQuery("from Orders");
//        List<Orders> listOrders = query.getResultList();
//        List<Items> cartItems = new ArrayList<>();
//        for (Orders o : listOrders) {
//            if (o.getClient() == null)
//                cartItems.addAll(o.getItems());
//        }
//        return cartItems;
//    }

//    @Override
//    public List<Items> getUsersShoppingCart(String userLogin) {//with Order table  using
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Object> clientsQuery = cb.createQuery();
//        Root<Clients> clientsRoot = clientsQuery.from(Clients.class);
//        clientsQuery.select(clientsRoot);
//
//        clientsQuery.where(cb.equal(clientsRoot.join("login").get("login"), userLogin));
//        Query clientQ = em.createQuery(clientsQuery);
//        int clientId = ((Clients) clientQ.getSingleResult()).getClientId();
//
//
//        CriteriaQuery<Object> ordersQuery = cb.createQuery();
//        Root<Orders> ordersRoot = ordersQuery.from(Orders.class);
//        ordersQuery.select(ordersRoot);
//        ordersQuery.where(cb.equal(ordersRoot.join("client").get("clientId") , clientId));
//        Query q = em.createQuery(ordersQuery);
//
//        return ((Orders) q.getSingleResult()).getItems();
//    }

    @Override
    public void addNewItem( int categoryId, String author,String format,String language,String itemName, double price,double weight,
                            String volume,int availableCount,String pic){
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
}
