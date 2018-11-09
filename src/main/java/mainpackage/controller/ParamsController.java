package mainpackage.controller;

import mainpackage.model.Clients;
import mainpackage.model.Items;
import mainpackage.model.Params;
import mainpackage.service.ParamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.List;

@Controller
public class ParamsController {

    ParamsService paramsService;

    @Autowired
    @Qualifier(value="ParamsService")
    public void setCustomersService(ParamsService ps){
        this.paramsService = ps;
    }

//    @RequestMapping(value = "listauthors/{categoryId}", method = RequestMethod.GET)
//    public String listParamsAuthors(Model model, @PathVariable("categoryId") int categoryId) {//rename method
//        List<Items> items = this.paramsService.listItems(categoryId);
//        List<String> listAuthors = this.paramsService.listAuthors();
//        model.addAttribute("listAuthors",listAuthors);
//        model.addAttribute("items", items);
//        return "param_author_list";
//    }

    @RequestMapping(value = "/listparams", method = RequestMethod.GET)
    public String listParams(Model model) {

        List<String> listAuthors = this.paramsService.listAuthors();
        model.addAttribute("listAuthors",listAuthors);

        List<String> listLanguages = this.paramsService.listLanguages();
        model.addAttribute("listLanguages",listLanguages);

        List<String> listFormats = this.paramsService.listFormats();
        model.addAttribute("listFormats",listFormats);

        return "params_list";
    }

    @RequestMapping(value = "/searchbyauthor/{author}", method = RequestMethod.GET)
    public String searchByAuthor(Model model,@PathVariable("author") String author, Principal principal) {

        //check whether the somebody is logged in or not
        model.addAttribute("checkprincipal", principal);

        List<Items> listItems = this.paramsService.listItemsByParam(author);
        System.out.println("List of items by author "+listItems);
        model.addAttribute("listItems",listItems);

        List<String> listLanguages = this.paramsService.listLanguages();
        model.addAttribute("listLanguages",listLanguages);

        List<String> listFormats = this.paramsService.listFormats();
        model.addAttribute("listFormats",listFormats);

//        return "items_by_author";
        return "catalog";
    }


}
