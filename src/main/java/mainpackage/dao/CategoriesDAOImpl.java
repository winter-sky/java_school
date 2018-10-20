package mainpackage.dao;

import mainpackage.model.Categories;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository("CategoriesDAO")
public class CategoriesDAOImpl implements CategoriesDAO {
    @PersistenceContext
    private EntityManager em;

    public List<Categories> listCategories(){
        int level = 0;
        Query query = em.createQuery("from Categories where category_level=:categoryLevel");
        return  query.setParameter("categoryLevel", level).getResultList();
    }
}
