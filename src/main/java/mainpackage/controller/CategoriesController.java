package mainpackage.controller;

import mainpackage.model.*;
import mainpackage.service.CategoriesService;
import mainpackage.service.ClientsService;
import mainpackage.service.ParamsService;
import mainpackage.type.Role;
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
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static mainpackage.type.Role.ROLE_USER;

@Controller
public class CategoriesController {

    private CategoriesService categoriesService;

    @Autowired
    @Qualifier(value="CategoriesService")
    public void setCategorieservice(CategoriesService cs){
        this.categoriesService = cs;
    }

    ParamsService paramsService;

    @Autowired
    @Qualifier(value="ParamsService")
    public void setParamsService(ParamsService ps){
        this.paramsService = ps;
    }

    private ClientsService clientsService;

    @Autowired
    @Qualifier(value="ClientsService")
    public void setClientsService(ClientsService cs){
        this.clientsService = cs;
    }

    @RequestMapping(value = "/removecategory/{categoryId}")
    public String removeItemPage (@PathVariable("categoryId") int categoryId) {
        this.categoriesService.removeCategory(categoryId);
        return "edit_category";
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

    @RequestMapping(value = "/listcategories", method = RequestMethod.GET)//start page
    public String listCategories(HttpSession session, Model model, Principal principal) {


        if(principal!=null){
            String userLogin = principal.getName();
            model.addAttribute("userLogin", userLogin);
            Clients client = this.clientsService.findClientByLogin(userLogin);
            if(client!=null){
                Roles clientRole =  client.getLogin().getRole();
                Role role = clientRole.getRole();
                if (role.equals(ROLE_USER)){
                    Cart initialusercart = (Cart)session.getAttribute("initialusercart");//create a nes user shopping cart
                    if (initialusercart == null)
                    {
                        initialusercart = new Cart();//create new user cart in session, not in DB
                        //need to create guest cart if it not exist
                        Cart guestcart = (Cart)session.getAttribute("guestcart");//guest cart from session, not from DB
                        if (guestcart == null)
                        {//guestcart = this.cartService.createGuestCart();//persist Cart in DB
                            guestcart = new Cart();//create new guest Cart in session, not in DB
                        }

                        List<Items> guestCartItems = new ArrayList<>();
                        if(guestcart.getItems()!=null) {//check whether guest cart empty or not
                            guestCartItems.addAll(guestcart.getItems());
                            initialusercart.setItems(guestCartItems);
                        }
                        //initialusercart.setClient(client);
                    }
                    session.setAttribute("initialusercart",initialusercart);
                }
            }

        }

        model.addAttribute("checkprincipal", principal);


        Categories rootCategory = this.categoriesService.getRootCategory();
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
    public String listItemsByCategory(Model model,@PathVariable("categoryId") int categoryId, Principal principal) {//rename method

        Categories category = this.categoriesService.findCategoryById(categoryId);
        List<Items> items = category.getItems();
        model.addAttribute("items", items);
        model.addAttribute("category", category);

        model.addAttribute("checkprincipal", principal);

        Categories rootCategory = this.categoriesService.getRootCategory();
        model.addAttribute("rootCategory", rootCategory);

        List<String> listAuthors = this.paramsService.listAuthors();
        model.addAttribute("listAuthors",listAuthors);

        List<String> listLanguages = this.paramsService.listLanguages();
        model.addAttribute("listLanguages",listLanguages);

        List<String> listFormats = this.paramsService.listFormats();
        model.addAttribute("listFormats",listFormats);

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
