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
    @Transactional
    public Categories getRootCategory(){return categoriesDAO.getRootCategory();}

    @Override
    @Transactional
    public Categories findCategoryById(int categoryId){return  categoriesDAO.findCategoryById(categoryId);}
//
//    @Override
//    @Transactional
//    public List<String> listAuthors(){ return categoriesDAO.listAuthors();};

    @Override
    @Transactional
    public List<Categories> showLowermostSubCategories(){return this.categoriesDAO.showLowermostSubCategories();}

    @Override
    @Transactional
    public List<Categories> showAllCategories(){return this.categoriesDAO.showAllCategories();}

    @Override
    @Transactional
    public void addNewCategory(int categoryId,String categoryName,int categoryLevel){
        this.categoriesDAO.addNewCategory(categoryId, categoryName, categoryLevel);}
}
