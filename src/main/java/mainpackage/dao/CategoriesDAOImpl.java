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
        Query query = em.createQuery("from Categories where category_level=:categoryLevel");//level field is rebundant
        return  query.setParameter("categoryLevel", level).getResultList();
    }

    public List<Categories> listSubCategories(int parentId){
        Query query = em.createQuery("from Categories where parent_id=:parentId");
        return  query.setParameter("parentId", parentId).getResultList();
    }

    public List<Categories> listAllCategories(){return em.createQuery("SELECT c FROM Categories c").getResultList();}
}
