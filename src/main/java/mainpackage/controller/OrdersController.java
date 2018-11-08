package mainpackage.controller;

import mainpackage.model.Cart;
import mainpackage.model.Items;
import mainpackage.model.OrderItems;
import mainpackage.model.Orders;
import mainpackage.service.OrdersService;
import mainpackage.type.DeliveryMethod;
import mainpackage.type.PaymentMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @Autowired
    @Qualifier(value = "OrdersService")
    public void setOrdersService(OrdersService os) {
        this.ordersService = os;
    }

    @RequestMapping(value="/paymentmethod",method = RequestMethod.GET)
    public String selectPaymentMethodPage (HttpSession session, Model model, Principal principal) {
        String login = principal.getName();
        model.addAttribute("message", login);
        model.addAttribute("payment", PaymentMethod.values());
        return "payment_method";
    }

    @RequestMapping(value="/selectpaymentmethod/{paymentMethod}/{userLogin}", method = RequestMethod.GET)
    public String selectPaymentMethod (HttpSession session, Model model, Principal principal, @PathVariable("paymentMethod")
    PaymentMethod paymentMethod,@PathVariable("userLogin") String userLogin ){
        String login = principal.getName();
        //model.addAttribute("message", login);
        this.ordersService.selectPaymentMethod(paymentMethod,login);
        //model.addAttribute("payment", PaymentMethod.values());
        return  "redirect:/deliverymethod";
    }

    @RequestMapping(value="/deliverymethod",method = RequestMethod.GET)
    public String selectDeliveryMethodPage (HttpSession session, Model model, Principal principal) {
        String login = principal.getName();
        model.addAttribute("message", login);
        model.addAttribute("delivery", DeliveryMethod.values());
        return "delivery_method";
    }

    @RequestMapping(value="/selectdeliverymethod/{deliveryMethod}/{userLogin}", method = RequestMethod.GET)
    public String selectDeliveryMethod (HttpSession session, Model model, Principal principal, @PathVariable("deliveryMethod")
            DeliveryMethod deliveryMethod, @PathVariable("userLogin") String userLogin ){
        String login = principal.getName();
        //model.addAttribute("message", login);
        this.ordersService.selectDeliveryMethod(deliveryMethod,login);
        //model.addAttribute("payment", PaymentMethod.values());
        return  "redirect:/hello";
    }

    @RequestMapping(value="/addtoorder/{userlogin}/{itemId}", method = RequestMethod.GET)
    public String addItemToOrder (SessionStatus status, HttpSession session, Model model, Principal principal, @PathVariable("userlogin")
            String userLogin , @PathVariable("itemId") int itemId ){
        System.out.println("Are we in the fucking controller?");
        String login = principal.getName();
        this.ordersService.addNewOrder(login,itemId);
        session.removeAttribute("guestcart");
        session.removeAttribute("initialusercart");
        //status.setComplete();//?
        return "redirect:/hello";
    }

    @RequestMapping(value="/getuserorder/{userlogin}", method = RequestMethod.GET)//get items in current order
    public String addItemToOrder (HttpSession session,Model model, Principal principal, @PathVariable("userlogin")
            String userLogin ){
        System.out.println("Are we in the fucking controller?");
        String login = principal.getName();
        //TODO
        List<Items> list = this.ordersService.getUserCurrentOrder(login);
        Orders currentOrder = this.ordersService.getCurrentOrder(login);
        List<OrderItems> orderItem = currentOrder.getOrderItems();
        System.out.println("orderitem " + orderItem);
        model.addAttribute("orderitem", orderItem);
        model.addAttribute("listorderitems", list);
        model.addAttribute("currentorder", currentOrder);
        return "current_user_order";
    }
}
