package mainpackage.controller;

import mainpackage.dto.ItemDTO;
import mainpackage.model.Cart;
import mainpackage.model.Categories;
import mainpackage.model.Items;
import mainpackage.service.CartService;
import mainpackage.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Controller
public class ItemsController {
    ItemsService itemsService;

    CartService cartService;

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

//    @RequestMapping(value = "/listitems", method = RequestMethod.GET)//doesn' work properly
//    public String listItems(Model model) {
//        model.addAttribute("items", new Items());
//        List<Items> list = this.itemsService.listItems();
//        model.addAttribute("listItems", list);
//        model.addAttribute("guestcart",new Cart());
//        return "list_items";
//    }

//    @RequestMapping(value = "/guestcart", method = RequestMethod.GET)//doesn' work properly
//    public String guestShoppingCart(HttpSession session,Model model) {
//        //List<Items> guestCart = this.itemsService.guestShoppingCart();
//
//        Cart guestcart = (Cart)session.getAttribute("guestcart");
//        List<Items> guest_cart = guestcart.getItems();
//        model.addAttribute("guest_cart", guest_cart);
//        return "guest_cart";
//    }

    @RequestMapping(value = "/itemlist", method = RequestMethod.GET)//create new Cart for guest
    public String scopeExample(HttpSession session, Model model, Principal principal) {

        //check whether the somebody is logged in or not
        model.addAttribute("checkprincipal", principal);

        List<ItemDTO> list = this.itemsService.listItems();
        System.out.println(list);
        model.addAttribute("listItems", list);

        session.setMaxInactiveInterval(3600);

        Cart guestcart = (Cart) session.getAttribute("guestcart");
        if (guestcart == null) {//guestcart = this.cartService.createGuestCart();//persist Cart in DB
            guestcart = new Cart();
            session.setAttribute("guestcart", guestcart);//place it into if block (properly or not??)
        }

        //return "list_items";
        return "catalog";
    }

    @RequestMapping(value = "/filterItems", method = RequestMethod.GET)//testing filter
    public String scopeExample(HttpSession session, Model model, String author, String language) {
        //TODO method which get two parameters
        return "test";
    }

//    @RequestMapping(value = "/usercart/{userLogin}", method = RequestMethod.GET)
//    public String userShoppingCart(Model model, @PathVariable("userLogin") String userLogin) {
//        List<Items> userCart = this.itemsService.userShoppingCart(userLogin);
//        model.addAttribute("usercart", userCart);
//        return "user_cart";
//    }
}
