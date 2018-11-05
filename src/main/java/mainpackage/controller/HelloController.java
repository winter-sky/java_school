package mainpackage.controller;

import mainpackage.model.Cart;
import mainpackage.model.Clients;
import mainpackage.model.Items;
import mainpackage.service.CartService;
import mainpackage.service.ClientsService;
import mainpackage.service.ItemsService;
import mainpackage.type.PaymentMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    public ItemsService itemsService;

    @Autowired
    protected CartService cartService;

    private ClientsService clientsService;

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


    @Autowired
    @Qualifier(value="ClientsService")
    public void setClientsService(ClientsService cs){
        this.clientsService = cs;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String helloPage (HttpSession session, Model model, Principal principal) {

        String login = principal.getName();
        model.addAttribute("message", login);

        model.addAttribute("payment", PaymentMethod.values());//just for testing

        Cart initialusercart = (Cart)session.getAttribute("initialusercart");//create a nes user shopping cart
        if (initialusercart == null)
        {
            Clients client = this.clientsService.findClientByLogin(login);
            initialusercart = this.cartService.createUserCart(client);//persist Cart in DB
            //initialusercart = new Cart();
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

        return "hello";
    }

    @RequestMapping(value = "/scopeSession", method = RequestMethod.GET)//just for testing
    public String scopeExample(HttpSession session) {
        System.out.println("ScopeController scopeSession() is called");
        session.setMaxInactiveInterval(3600);
        session.setAttribute("sessionObject", "Value is session object");
        return "test";
    }

}