package mainpackage.controller;

import mainpackage.model.Categories;
import mainpackage.model.Items;
import mainpackage.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class CategoriesController {
    private CategoriesService categoriesService;

    @Autowired
    @Qualifier(value="CategoriesService")
    public void setCustomersService(CategoriesService cs){
        this.categoriesService = cs;
    }

    @RequestMapping(value = "/catalog", method = RequestMethod.GET)
    public String showCatalog() {

        return "catalog";
    }

    @RequestMapping(value = "/listcategories", method = RequestMethod.GET)
    public String listCategories(Model model) {
        //model.addAttribute("categories", new Categories());
        List<Categories> list = this.categoriesService.listCategories();
        model.addAttribute("listCategories", list);
        return "categories";
    }

    @RequestMapping(value = "/listsubcategories/{parentId}", method = RequestMethod.GET)
    public String listSubCategories(Model model,@PathVariable("parentId") int parentId) {
        //model.addAttribute("categories", new Categories());
        List<Categories> list = this.categoriesService.listSubCategories(parentId);
        model.addAttribute("listSubCategories", list);
        return "subcategories";
    }
}
