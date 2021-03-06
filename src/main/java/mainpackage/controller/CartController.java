package mainpackage.controller;

import mainpackage.model.Cart;
import mainpackage.model.Clients;
import mainpackage.model.Items;
import mainpackage.service.CartService;
import mainpackage.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/cart")
public class CartController {

    CartService cartService;

    ItemsService itemsService;

    @Autowired
    @Qualifier(value = "CartService")
    public void setCartService(CartService cs) {
        this.cartService = cs;
    }

    @Autowired
    @Qualifier(value = "ItemsService")
    public void setItemsService(ItemsService is) {
        this.itemsService = is;
    }

    @RequestMapping(value = "/guestcart", method = RequestMethod.GET)
    public String guestShoppingCart(HttpSession session,Model model) {

        Cart guestcart = (Cart)session.getAttribute("guestcart");

        if (guestcart == null)
        {//guestcart = this.cartService.createGuestCart();//persist Cart in DB
            guestcart = new Cart();
            session.setAttribute("guestcart",guestcart);//is it need to be in if-block
        }

        List<Items> guest_cart;
        if(guestcart.getItems()==null) {
            List<Items> items = new ArrayList<>();
            guestcart.setItems(items);
            guest_cart=guestcart.getItems();
        }else guest_cart=guestcart.getItems();
        model.addAttribute("guest_cart", guest_cart);
        return "guest_cart";
    }

    @RequestMapping(value = "/usercart/{userLogin}", method = RequestMethod.GET)
    public String userShoppingCart(HttpSession session,Model model,Principal principal, @PathVariable("userLogin") String userLogin) {
        Cart usercart = (Cart)session.getAttribute("initialusercart");
        if(usercart!=null)
        model.addAttribute("user_cart", usercart.getItems());

        String login = principal.getName();
        model.addAttribute("userlogin", login);

        return "user_cart";
    }

    @RequestMapping(value= "/additem/{itemId}/{guestCartId}", method = RequestMethod.GET)//add new book to session guest
    // cart, not  in DB and return guest cart
    public String addItem(HttpSession session,@PathVariable("itemId") int itemId, Model model,@PathVariable("guestCartId")int guestCartId){
       Cart guestcart = (Cart)session.getAttribute("guestcart");//guest cart from session, not from DB

        Items item = this.itemsService.findItemById(itemId);

        if(guestcart.getItems()==null) {
            List<Items> items = new ArrayList<>();
            items.add(item);
            guestcart.setItems(items);
        }
        else guestcart.addItem(item);

        List<Items> guest_cart = guestcart.getItems();
        model.addAttribute("guest_cart", guest_cart);//guest cart

        return "guest_cart";
    }


    @RequestMapping(value="/additemtousercart/{itemId}/{userCartId}", method = RequestMethod.GET)
    public String addItemToUserCart(HttpSession session, Principal principal,@PathVariable("itemId") int itemId, Model model,
                                    @PathVariable("userCartId") int userCartId){
        Items item = this.itemsService.findItemById(itemId);
        Cart usercart = (Cart)session.getAttribute("initialusercart");
        if(usercart.getItems()==null) {
            List<Items> items = new ArrayList<>();
            items.add(item);//add new book in the session user Cart, not in the DB user Cart
            usercart.setItems(items);
        }
        else usercart.addItem(item);

        List<Items> usercartlist = usercart.getItems();
        model.addAttribute("user_cart", usercartlist);//user cart

        String login = principal.getName();
        model.addAttribute("userlogin", login);

        return "user_cart";
    }

}