package mainpackage.controller;

import mainpackage.model.Customers;
import mainpackage.service.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CustomersController {

    private CustomersService customersService;

    @Autowired
    @Qualifier(value="CustomersService")
    public void setCustomersService(CustomersService cs){
        this.customersService = cs;
    }

    @RequestMapping(value = "/listcustomers", method = RequestMethod.GET)
    public String listCustomers(Model model) {
        model.addAttribute("customers", new Customers());
        List<Customers> list = this.customersService.listCustomers();
        System.out.println(list);
        model.addAttribute("listCustomers", list);

        return "listcustomers";
    }
}
