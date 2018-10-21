package mainpackage.service;

import mainpackage.dao.CategoriesDAO;
import mainpackage.model.Categories;
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

    @Override
    @Transactional
    public List<Categories> listCategories(){return categoriesDAO.listCategories();}

    @Override
    @Transactional
    public List<Categories> listSubCategories(int parentId){return  categoriesDAO.listSubCategories(parentId);}

    @Override
    @Transactional
    public List<Categories> listAllCategories(){return  categoriesDAO.listAllCategories();}
}
