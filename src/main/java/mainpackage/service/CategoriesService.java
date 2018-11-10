package mainpackage.service;

import mainpackage.model.Categories;
import mainpackage.model.Items;

import java.util.List;

public interface CategoriesService {

    Categories getRootCategory();

    Categories findCategoryById(int categoryId);

    List<Categories> showLowermostSubCategories();

    List<Categories> showAllCategories();

    void addNewCategory(int categoryId,String categoryName,int categoryLevel);

    void updateCategory (int categoryId,int parentId,String categoryName);

    List<Categories> showAllParentCategories();

    void removeCategory(int categoryId);
}
