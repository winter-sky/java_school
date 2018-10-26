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

//    public List<Categories> listCategories(){
//        int level = 0;
//        Query query = em.createQuery("from Categories where category_level=:categoryLevel");//level field is rebundant
//        return  query.setParameter("categoryLevel", level).getResultList();
//    }
//
//    public List<Categories> listSubCategories(int parentId){
//        Query query = em.createQuery("from Categories where parent_id=:parentId");
//        return  query.setParameter("parentId", parentId).getResultList();
//    }
//
//    public List<Categories> listAllCategories(){return em.createQuery("SELECT c FROM Categories c").getResultList();}

    public Categories getRootCategory(){
        int level = 0;
        Query query = em.createQuery("from Categories where category_level=:categoryLevel");//level field is rebundant
        return  (Categories) query.setParameter("categoryLevel", level).getSingleResult();
    }

    public Categories findCategoryById(int categoryId){
        Query query = em.createQuery("from Categories where category_id=:categoryId");
        return  (Categories) query.setParameter("categoryId", categoryId).getSingleResult();
    }

//    @Override
//    public List<String> listAuthors(){
//        Query query = em.createQuery("from Params");
//        List<Params> listParams = query.getResultList();
//        List<String> listAuthors=new ArrayList<>();
//        for(Params p:listParams){
//            listAuthors.add( p.getAuthor());
//        }
//
//        List<String> listWithoutDuplicates = new ArrayList<>(
//                new HashSet<>(listAuthors));
//
//        return listWithoutDuplicates;
//    }
}
