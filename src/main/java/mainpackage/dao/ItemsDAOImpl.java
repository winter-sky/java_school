package mainpackage.dao;

import mainpackage.model.Categories;
import mainpackage.model.Items;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository("ItemsDAO")
public class ItemsDAOImpl implements ItemsDAO {
    @PersistenceContext
    private EntityManager em;

    public List<Items> listItems(){
        return em.createQuery("SELECT i FROM Items i").getResultList();
    }

    public List<Items> showItemsByCategory(int categoryId){
        Query query = em.createQuery("from Items where item_category=:categoryId");
        return query.setParameter("categoryId", categoryId).getResultList();
    }
}
