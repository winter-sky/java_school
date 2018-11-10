package mainpackage.controller;

import mainpackage.model.Cart;
import mainpackage.model.Items;
import mainpackage.model.OrderItems;
import mainpackage.model.Orders;
import mainpackage.service.OrdersService;
import mainpackage.type.DeliveryMethod;
import mainpackage.type.OrderStatus;
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

    @RequestMapping(value="/selectorderstatus/{orderStatus}/{orderId}", method = RequestMethod.GET)
    public String selectOrderStatus (Model model,@PathVariable("orderStatus")
            OrderStatus orderStatus,@PathVariable("orderId") int orderId ){

        this.ordersService.selectOrderStatus(orderStatus, orderId);

        return  "redirect:/getallorders";
    }

    @RequestMapping(value="/changeorderstatus/{orderId}",method = RequestMethod.GET)
    public String selectOrderStatusPage (@PathVariable("orderId") int orderId, Model model) {
        //TODO get order by id, make order attribute
        Orders order = this.ordersService.findOrderById(orderId);
        model.addAttribute("orderstatus", OrderStatus.values());
        model.addAttribute("order", order);
        return "order_status";
    }

    @RequestMapping(value="/payfortheorder/{userLogin}",method = RequestMethod.GET)
    public String payForTheOrder (HttpSession session, Model model, Principal principal,
                                  @PathVariable("userLogin") String userLogin) {//??
        String login = principal.getName();//??
        this.ordersService.payForTheOrder(login);
        //return "redirect:/getuserorder/"+login;
        return "redirect:/hello";
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
        model.addAttribute("message", login);
        this.ordersService.selectDeliveryMethod(deliveryMethod,login);
        //model.addAttribute("payment", PaymentMethod.values());
        return  "complete_order";
    }

    @RequestMapping(value="/addtoorder/{userlogin}/{itemId}", method = RequestMethod.GET)
    public String addItemToOrder (SessionStatus status, HttpSession session, Model model, Principal principal, @PathVariable("userlogin")
            String userLogin , @PathVariable("itemId") int itemId ){
        String login = principal.getName();
        this.ordersService.addNewOrder(login,itemId);
        session.removeAttribute("guestcart");
        session.removeAttribute("initialusercart");
        //status.setComplete();//?
        return "redirect:/hello";
    }

    @RequestMapping(value="/getuserorder/{userlogin}", method = RequestMethod.GET)//get items in current order,
    public String getCurrentOrder (HttpSession session,Model model, Principal principal, @PathVariable("userlogin")
            String userLogin ){
        String login = principal.getName();

        List<Items> list = this.ordersService.getUserCurrentOrder(login);//current awaiting payment order items
        Orders currentOrder = this.ordersService.getCurrentOrder(login);//curent awaiting payment order

        List<OrderItems> orderItem = new ArrayList<>();

        if(currentOrder!=null)
        orderItem=currentOrder.getOrderItems();

        model.addAttribute("orderitem", orderItem);
        model.addAttribute("listorderitems", list);
        model.addAttribute("currentorder", currentOrder);
        return "current_user_order";
    }

    @RequestMapping(value="/getuserorders/{userLogin}", method = RequestMethod.GET)//show user orders with not delivered status
    public String getOrders (HttpSession session,Model model, Principal principal, @PathVariable("userLogin")
            String userLogin ){
        String login = principal.getName();

        List<Orders> list = this.ordersService.getOrders(userLogin);

        model.addAttribute("orderlist", list);

        return "user_orders";
    }

    @RequestMapping(value="/getallorders", method = RequestMethod.GET)
    public String getAllOrders (Model model){

        List<Orders> listAllOrders = this.ordersService.showAllOrdersForAdmin();

        model.addAttribute("listallorders", listAllOrders);

        return "all_orders";
    }
}
