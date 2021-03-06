package mainpackage.dao;

import mainpackage.model.Categories;
import mainpackage.model.Items;

import java.util.List;

public interface CategoriesDAO {
    Categories getRootCategory();

    Categories findCategoryById(int categoryId);

    List<Categories> showLowermostSubCategories();//show lowermost categories (categories without shild categories)

    List<Categories> showAllCategories();

    void addNewCategory(int categoryId,String categoryName,int categoryLevel);

    void updateCategory (int categoryId,int parentId,String categoryName);

    List<Categories> showAllParentCategories();

    void removeCategory(int categoryId);
}
