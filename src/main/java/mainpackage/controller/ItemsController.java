package mainpackage.controller;

import mainpackage.dto.ItemDTO;
import mainpackage.model.Cart;
import mainpackage.model.Categories;
import mainpackage.model.Items;
import mainpackage.service.CartService;
import mainpackage.service.CategoriesService;
import mainpackage.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Controller
public class ItemsController {
    ItemsService itemsService;

    CartService cartService;

    private CategoriesService categoriesService;

    @Autowired
    @Qualifier(value="CategoriesService")
    public void setCategoriesService(CategoriesService cs){
        this.categoriesService = cs;
    }

    @Autowired
    @Qualifier(value = "ItemsService")
    public void setItemsService(ItemsService is) {
        this.itemsService = is;
    }

    @Autowired
    @Qualifier(value = "CartService")
    public void setCartService(CartService cs) {
        this.cartService = cs;
    }

//    @RequestMapping(value = "/removeitem/{itemId}")
//    public String removeItemPage (@PathVariable("itemId") int itemId) {
//        this.itemsService.removeItem(itemId);
//        return "redirect:/edit_item";
//    }

    @RequestMapping(value = "/edititempage", method = RequestMethod.GET)
    public String editItemPage (Model model) {
        List<Items> listAllItems = this.itemsService.showListAllItems();
        model.addAttribute("listallItems", listAllItems);
        return "edit_item";
    }

    @RequestMapping(value = "/edititem/{itemId}", method = RequestMethod.GET)
    public String editItem (Model model,@PathVariable("itemId") int itemId) {
        Items item = this.itemsService.findItemById(itemId);
        model.addAttribute("item", item);
        List<Items> listAllItems = this.itemsService.showListAllItems();
        model.addAttribute("listallItems", listAllItems);
        List<Categories> listLowermostCategories = this.categoriesService.showLowermostSubCategories();
        model.addAttribute("listlowermostcategories", listLowermostCategories);
        return "edit_item";
    }

    @RequestMapping(value= "/updateitem", method = RequestMethod.POST)
    public String updateItem(Model model,@RequestParam("itemId") int itemId,@RequestParam("itemName") String itemName,@RequestParam("price") double price,
      @RequestParam("weight") double weight, @RequestParam("volume") String volume,
         @RequestParam("availableCount") int availableCount,@RequestParam("pic") String pic,
            @RequestParam("categoryId") int categoryId,@RequestParam("author") String author,
                @RequestParam("format") String format,@RequestParam("language") String language){
        this.itemsService.updateItem(itemId, itemName, price, weight, volume, availableCount, pic, categoryId, author,
                format, language);
        List<Items> listAllItems = this.itemsService.showListAllItems();
        model.addAttribute("listallItems", listAllItems);
        return "edit_item";
    }

    @RequestMapping(value = "/createnewitem", method = RequestMethod.GET)
    public String createItemPage (Model model) {
        List<Categories> lowermostCategories = this.categoriesService.showLowermostSubCategories();
        model.addAttribute("listlowermostcategories", lowermostCategories);
        return "create_item";
    }

    @RequestMapping(value = "/createitem", method = RequestMethod.POST)
    public String createnewItem (Model model, @RequestParam("categoryId") int categoryId,
   @RequestParam("author") String author, @RequestParam("format") String format,@RequestParam("language") String language,
                                 @RequestParam("itemName") String itemName,
     @RequestParam("price") double price,@RequestParam("weight") double weight,@RequestParam("volume") String volume,
   @RequestParam("availableCount") int availableCount,@RequestParam("pic") String pic){
        List<Categories> lowermostCategories = this.categoriesService.showLowermostSubCategories();
        this.itemsService.addNewItem(categoryId, author, format, language, itemName, price, weight, volume, availableCount, pic);
        model.addAttribute("listlowermostcategories", lowermostCategories);
        return "create_item";
    }

    @RequestMapping(value = "/itemlist", method = RequestMethod.GET)//show list all items, create new Cart for guest
    public String scopeExample(HttpSession session, Model model, Principal principal) {

        //check whether the somebody is logged in or not
        model.addAttribute("checkprincipal", principal);
        List<ItemDTO> list = this.itemsService.listItems();
        model.addAttribute("listItems", list);

        Cart guestcart = (Cart) session.getAttribute("guestcart");
        if (guestcart == null) {//guestcart = this.cartService.createGuestCart();//persist Cart in DB
            guestcart = new Cart();
            session.setAttribute("guestcart", guestcart);//place it into if block (properly or not??)
        }

        return "catalog";
        //return "redirect:/listcategories";
    }

    @RequestMapping(value = "/filterItems", method = RequestMethod.GET)//testing filter
    public String scopeExample(HttpSession session, Model model, String author, String language) {
        //TODO method which get two parameters
        return "test";
    }

}
