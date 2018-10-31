package mainpackage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.ModelMap;

import java.security.Principal;

@Controller
@RequestMapping("/hello")
public class HelloController {
    @RequestMapping(method = RequestMethod.GET)
//    public String printHello(ModelMap model) {
//        model.addAttribute("message", "Hello Spring MVC Framework!");
//        return "hello";
//    }
    public String helloPage (Model model, Principal principal) {
        String username = principal.getName();
        //model.addAttribute("message", "You are logged in as " + principal.getName());
        model.addAttribute("message", username);
        return "hello";
    }
}