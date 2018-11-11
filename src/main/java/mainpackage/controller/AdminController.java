package mainpackage.controller;

import mainpackage.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class AdminController {

    @Autowired
    private OrdersService ordersService;

    @Autowired
    @Qualifier(value = "OrdersService")
    public void setOrdersService(OrdersService os) {
        this.ordersService = os;
    }

    @RequestMapping(value = "/adminpage", method = RequestMethod.GET)
    public String adminPage() {

        return "admin_page";
    }

    @RequestMapping(value = "/getmonthproceeds", method = RequestMethod.GET)
    public String getMonthProceed(Model model) {
        double monthProceeds=this.ordersService.showMonthProceeds();
        model.addAttribute("monthproceeds", monthProceeds);
        return "show_proceeds";
    }
}
