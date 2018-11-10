package mainpackage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class AdminController {
    @RequestMapping(value = "/adminpage", method = RequestMethod.GET)
    public String adminPage() {

        return "admin_page";
    }
}
