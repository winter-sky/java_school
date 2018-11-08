package mainpackage.service;

import mainpackage.model.Categories;
import mainpackage.model.Items;

import java.util.List;

public interface CategoriesService {
//    List<Categories> listCategories();
//    List<Categories> listSubCategories(int parentId);
//    List<Categories> listAllCategories();
    Categories getRootCategory();

    Categories findCategoryById(int categoryId);

//    List<String> listAuthors();

    List<Categories> showLowermostSubCategories();
}
