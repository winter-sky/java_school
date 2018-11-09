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

    @Override
    public List<Categories> showAllParentCategories(){
        Query query = em.createQuery("from Categories");
        List<Categories> listAllCategories = query.getResultList();

        List<Categories> listAllParentCategories = new ArrayList<>();

        for (Categories c:listAllCategories){
            if(!c.getSubCategories().isEmpty())
                listAllParentCategories.add(c);
        }
        return listAllParentCategories;
    }

    @Override
    public void updateCategory (int categoryId,int parentId,String categoryName){
        //find a category to be changed
        Categories categoryDB = findCategoryById(categoryId);

        //find category to be parent category
        Categories parentCategory = findCategoryById(parentId);

        //update category in DB
        categoryDB.setCategoryName(categoryName);
        categoryDB.setCategoryLevel(parentCategory.getCategoryLevel()+1);
        categoryDB.setParent(parentCategory);
    }

    @Override
    public Categories getRootCategory() {
        int level = 0;
        Query query = em.createQuery("from Categories where category_level=:categoryLevel");//level field is rebundant
        return (Categories) query.setParameter("categoryLevel", level).getSingleResult();
    }

    @Override
    public Categories findCategoryById(int categoryId) {
        Query query = em.createQuery("from Categories where category_id=:categoryId");
        return (Categories) query.setParameter("categoryId", categoryId).getSingleResult();
    }

    @Override
    public void addNewCategory(int categoryId,String categoryName,int categoryLevel){
        //find category with id received
        Query query = em.createQuery("from Categories where category_id=:categoryId");
        Categories parentCategory= (Categories) query.setParameter("categoryId", categoryId).getSingleResult();

        //create new Category
        Categories newCategory = new Categories();
        newCategory.setCategoryName(categoryName);
        newCategory.setCategoryLevel(categoryLevel);
        newCategory.setParent(parentCategory);

        em.persist(newCategory);
    }

    @Override
    public List<Categories> showLowermostSubCategories(){
        Query query = em.createQuery("from Categories");

        List<Categories> result = new ArrayList<>();
        List<Categories> listAllCategories = query.getResultList();
        for (Categories c:listAllCategories){
            if(c.getSubCategories().isEmpty())
                result.add(c);
        }

        return  result;
    }

    @Override
    public List<Categories> showAllCategories(){
        Query query = em.createQuery("from Categories");
        List<Categories> listAllCategories = query.getResultList();
        return  listAllCategories;
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


    @Override
    @SuppressWarnings("unchecked")
    public List<Categories> getAllCategories() {
        return em.createQuery("from Categories").getResultList();
    }
}



