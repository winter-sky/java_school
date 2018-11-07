package mainpackage.controller;

import mainpackage.model.Items;
import mainpackage.service.OrdersService;
import mainpackage.type.PaymentMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @Autowired
    @Qualifier(value = "OrdersService")
    public void setOrdersService(OrdersService os) {
        this.ordersService = os;
    }


    @RequestMapping(value="/selectpaymentmethod/{paymentMethod}/{userLogin}", method = RequestMethod.GET)
    public String selectPaymentMethod (HttpSession session, Model model, Principal principal, @PathVariable("paymentMethod")
    PaymentMethod paymentMethod,@PathVariable("userLogin") String userLogin ){
        String login = principal.getName();
        this.ordersService.selectPaymentMethod(paymentMethod,login);
        return  "hello";
    }

    @RequestMapping(value="/addtoorder/{userlogin}/{itemId}", method = RequestMethod.GET)
    public String addItemToOrder (HttpSession session,Model model, Principal principal, @PathVariable("userlogin")
            String userLogin ,@PathVariable("itemId") Integer itemId ){
        System.out.println("Are we in the fucking controller?");
        String login = principal.getName();
        this.ordersService.addNewOrder(login,itemId);
        return "redirect:/hello";
    }

    @RequestMapping(value="/getuserorder/{userlogin}", method = RequestMethod.GET)
    public String addItemToOrder (HttpSession session,Model model, Principal principal, @PathVariable("userlogin")
            String userLogin ){
        System.out.println("Are we in the fucking controller?");
        String login = principal.getName();
        //TODO
        //this.ordersService.getUserCurrentOrder(login);
        return "current_user_order";
    }
}
