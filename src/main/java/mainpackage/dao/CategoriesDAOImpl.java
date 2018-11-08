package mainpackage.dao;

import mainpackage.model.Categories;
import mainpackage.model.Items;
import mainpackage.model.Params;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Repository("CategoriesDAO")
public class CategoriesDAOImpl implements CategoriesDAO {
    @PersistenceContext
    private EntityManager em;

    public Categories getRootCategory() {
        int level = 0;
        Query query = em.createQuery("from Categories where category_level=:categoryLevel");//level field is rebundant
        return (Categories) query.setParameter("categoryLevel", level).getSingleResult();
    }

    public Categories findCategoryById(int categoryId) {
        Query query = em.createQuery("from Categories where category_id=:categoryId");
        return (Categories) query.setParameter("categoryId", categoryId).getSingleResult();
    }

    @Override
    public List<Categories> showLowermostSubCategories(){
        Query query = em.createQuery("from Categories");

        List<Categories> result = new ArrayList<>();
        List<Categories> listAllCategories = query.getResultList();
        for (Categories c:listAllCategories){
            if(c.getCategories().isEmpty())
                result.add(c);
        }

        return  result;
    }
}



