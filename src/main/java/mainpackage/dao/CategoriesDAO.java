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

//    List<String> listAuthors();

    List<Categories> showLowermostSubCategories();//show lowermost categories (categories without shild categories)

    List<Categories> showAllCategories();

    void addNewCategory(int categoryId,String categoryName,int categoryLevel);
}
