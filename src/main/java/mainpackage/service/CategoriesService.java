package mainpackage.service;

import mainpackage.model.Categories;

import java.util.List;

public interface CategoriesService {
    List<Categories> listCategories();
    List<Categories> listSubCategories(int parentId);
}
