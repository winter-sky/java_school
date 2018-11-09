package mainpackage.controller;

import mainpackage.model.Cart;
import mainpackage.model.Categories;
import mainpackage.model.Items;
import mainpackage.service.CategoriesService;
import mainpackage.service.ParamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CategoriesController {

    private CategoriesService categoriesService;

    ParamsService paramsService;

    @Autowired
    @Qualifier(value="ParamsService")
    public void setCustomersService(ParamsService ps){
        this.paramsService = ps;
    }

    @Autowired
    @Qualifier(value="CategoriesService")
    public void setCustomersService(CategoriesService cs){
        this.categoriesService = cs;
    }

    @RequestMapping(value = "/editcategorypage", method = RequestMethod.GET)
    public String editCategoryPage (Model model) {
        List<Categories> listAllCategories = this.categoriesService.showAllCategories();
        model.addAttribute("listallcategories", listAllCategories);
        return "edit_category";
    }

    @RequestMapping(value = "/editcategory/{categoryId}", method = RequestMethod.GET)
    public String editCategoryById (Model model,@PathVariable("categoryId") int categoryId) {
        Categories category = this.categoriesService.findCategoryById(categoryId);
        model.addAttribute("category", category);
        List<Categories> listAllParentCategories = this.categoriesService.showAllParentCategories();
        model.addAttribute("listallparentcategories", listAllParentCategories);
        return "edit_category";
    }

    @RequestMapping(value= "/updatecategory", method = RequestMethod.POST)
    public String updateCategory(Model model,@RequestParam("categoryId") int categoryId,@RequestParam("parentId") int parentId,@RequestParam("categoryName") String categoryName){
        this.categoriesService.updateCategory(categoryId, parentId, categoryName);
        List<Categories> listAllCategories = this.categoriesService.showAllCategories();
        model.addAttribute("listallcategories", listAllCategories);
        return "edit_category";
    }

    @RequestMapping(value = "/createcategorypage", method = RequestMethod.GET)//testing filter
    public String createCategoryPage (Model model) {
        List<Categories> listAllParentCategories = this.categoriesService.showAllParentCategories();
        model.addAttribute("listallparentcategories", listAllParentCategories);
        return "create_category";
    }

    @RequestMapping(value = "/addnewcategory", method = RequestMethod.POST)//testing filter
    public String createNewCategory (Model model, @RequestParam("categoryId") int categoryId,
     @RequestParam("categoryName") String categoryName,@RequestParam("categoryLevel") int categoryLevel){
        //TODO
        this.categoriesService.addNewCategory(categoryId, categoryName, categoryLevel);
        List<Categories> listallcategories = this.categoriesService.showAllCategories();
        model.addAttribute("listallcategories", listallcategories);
        return "create_category";}

    @RequestMapping(value = "/catalog", method = RequestMethod.GET)
    public String showCatalog() {

        return "catalog";
    }

    @RequestMapping(value = "/listcategories", method = RequestMethod.GET)
    public String listCategories(Model model) {
        //model.addAttribute("categories", new Categories());
//        List<Categories> list = this.categoriesService.listCategories();
//        model.addAttribute("listCategories", list);
        Categories rootCategory = this.categoriesService.getRootCategory();
        System.out.println(rootCategory);
        model.addAttribute("rootCategory", rootCategory);
        List<String> listAuthors = this.paramsService.listAuthors();

        model.addAttribute("listAuthors",listAuthors);

        List<String> listLanguages = this.paramsService.listLanguages();
        model.addAttribute("listLanguages",listLanguages);

        List<String> listFormats = this.paramsService.listFormats();
        model.addAttribute("listFormats",listFormats);

        return "catalog";
    }

    @RequestMapping(value = "showitemsbycategory/{categoryId}", method = RequestMethod.GET)//filter by category
    public String listSubCategories(Model model,@PathVariable("categoryId") int categoryId) {//rename method

        Categories category = this.categoriesService.findCategoryById(categoryId);
        List<Items> items = category.getItems();
//        List<String> listAuthors = this.categoriesService.listAuthors();
//        model.addAttribute("listAuthors",listAuthors);
        model.addAttribute("items", items);
        model.addAttribute("category", category);
//        return "listitemsbycategory";
        return "catalog";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(HttpSession session, Model model) {
        Categories rootCategory = this.categoriesService.getRootCategory();
        System.out.println(rootCategory);
        model.addAttribute("rootCategory", rootCategory);
        List<String> listAuthors = this.paramsService.listAuthors();

        model.addAttribute("listAuthors",listAuthors);

        List<String> listLanguages = this.paramsService.listLanguages();
        model.addAttribute("listLanguages",listLanguages);

        List<String> listFormats = this.paramsService.listFormats();
        model.addAttribute("listFormats",listFormats);
        return "test";
    }
}
