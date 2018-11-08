package mainpackage.dao;

import mainpackage.model.Categories;
import mainpackage.model.Items;

import java.util.List;

public interface CategoriesDAO {
//    List<Categories> listCategories();
//    List<Categories> listSubCategories(int parentId);
//    List<Categories> listAllCategories();
    Categories getRootCategory();

    Categories findCategoryById(int categoryId);

    List<Categories> getAllCategories();

//    List<String> listAuthors();
}
