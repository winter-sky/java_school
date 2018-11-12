package mainpackage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainPageController {
    @RequestMapping(value = "/backtomainpage", method = RequestMethod.GET)
    public String backToMainPage() {

        return "redirect:/listcategories";
    }
}
