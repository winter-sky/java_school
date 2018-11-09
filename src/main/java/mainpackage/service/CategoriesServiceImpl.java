package mainpackage.service;

import mainpackage.dao.CategoriesDAO;
import mainpackage.model.Categories;
import mainpackage.model.Items;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("CategoriesService")
@Transactional
public class CategoriesServiceImpl implements CategoriesService {

    @Autowired
    @Qualifier("CategoriesDAO")
    private CategoriesDAO categoriesDAO;

//    @Override
//    @Transactional
//    public List<Categories> listCategories(){return categoriesDAO.listCategories();}
//
//    @Override
//    @Transactional
//    public List<Categories> listSubCategories(int parentId){return  categoriesDAO.listSubCategories(parentId);}
//
//    @Override
//    @Transactional
//    public List<Categories> listAllCategories(){return  categoriesDAO.listAllCategories();}

    @Override
    public Categories getRootCategory(){return categoriesDAO.getRootCategory();}

    @Override
    public Categories findCategoryById(int categoryId){return  categoriesDAO.findCategoryById(categoryId);}
//
//    @Override
//    @Transactional
//    public List<String> listAuthors(){ return categoriesDAO.listAuthors();};

    @Override
    public List<Categories> showLowermostSubCategories(){return this.categoriesDAO.showLowermostSubCategories();}

    @Override
    public List<Categories> showAllCategories(){return this.categoriesDAO.showAllCategories();}

    @Override
    public void addNewCategory(int categoryId,String categoryName,int categoryLevel){
        this.categoriesDAO.addNewCategory(categoryId, categoryName, categoryLevel);}

    @Override
     public void updateCategory (int categoryId,int parentId,String categoryName){
        this.categoriesDAO.updateCategory(categoryId, parentId, categoryName);
     }

    @Override
    public List<Categories> showAllParentCategories(){return this.categoriesDAO.showAllParentCategories();}

    @Override
    public void removeCategory(int categoryId){
        this.categoriesDAO.removeCategory(categoryId);
    }
}
