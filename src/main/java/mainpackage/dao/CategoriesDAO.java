package mainpackage.dao;

import mainpackage.model.Categories;

import java.util.List;

public interface CategoriesDAO {
    List<Categories> listCategories();
    List<Categories> listSubCategories(int parentId);
}
