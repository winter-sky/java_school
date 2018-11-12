package mainpackage.dao;

import mainpackage.model.Categories;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository("CategoriesDAO")
public class CategoriesDAOImpl implements CategoriesDAO {
    public static final int LEVEL_ROOT = 0;
    @PersistenceContext
    private EntityManager em;

    @Override
    public void removeCategory(int categoryId){//must be improved
//        Categories category = em.find(Categories.class, categoryId);
//        em.remove(category);
    }

    @Override
    public List<Categories> showAllParentCategories(){
        Query query = em.createQuery("from Categories");

        List<Categories> listAllCategories = query.getResultList();

        List<Categories> listAllParentCategories = new ArrayList<>();

        for (Categories c:listAllCategories){
            if(!c.getCategories().isEmpty()) {
                listAllParentCategories.add(c);
            }
        }

        return listAllParentCategories;
    }

    @Override
    public void updateCategory (int categoryId, int parentId, String categoryName){
        //find a category to be changed
        Categories cat = findCategoryById(categoryId);

        //find category to be parent category
        Categories parentCategory = findCategoryById(parentId);

        //update category in DB
        cat.setCategoryName(categoryName);
        cat.setCategoryLevel(parentCategory.getCategoryLevel()+1);
        cat.setCategory(parentCategory);
    }

    @Override
    public Categories getRootCategory() {
        Query query = em.createQuery("from Categories where category_level=:categoryLevel");

        return (Categories) query.setParameter("categoryLevel", LEVEL_ROOT).getSingleResult();
    }

    @Override
    public Categories findCategoryById(int categoryId) {
        Query query = em.createQuery("from Categories where category_id=:categoryId");

        return (Categories) query.setParameter("categoryId", categoryId).getSingleResult();
    }

    @Override
    public void addNewCategory(int parentId, String categoryName, int categoryLevel){
        //find category with id received
        Query query = em.createQuery("from Categories where category_id=:parentId");

        Categories parentCategory= (Categories) query.setParameter("parentId", parentId).getSingleResult();

        //create new Category
        Categories newCat = new Categories();

        newCat.setCategoryName(categoryName);
        newCat.setCategoryLevel(categoryLevel);
        newCat.setCategory(parentCategory);

        em.persist(newCat);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Categories> showLowermostSubCategories(){
        Query query = em.createQuery("from Categories");

        List<Categories> result = new ArrayList<>();

        List<Categories> listAllCategories = (List<Categories>)query.getResultList();

        for (Categories c:listAllCategories){
            if(c.getCategories().isEmpty()) {
                result.add(c);
            }
        }

        return  result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Categories> showAllCategories(){
        Query query = em.createQuery("from Categories");

        List<Categories> listAllCategories = (List<Categories>)query.getResultList();

        return  listAllCategories;
    }
}



