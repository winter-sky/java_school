package mainpackage.controller;

import mainpackage.dto.ItemDTO;
import mainpackage.model.*;
import mainpackage.service.CategoriesService;
import mainpackage.service.ItemsService;
import mainpackage.service.ParamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
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

    private CategoriesService categoriesService;

    @Autowired
    @Qualifier(value="CategoriesService")
    public void setCategorieservice(CategoriesService cs){
        this.categoriesService = cs;
    }

    ItemsService itemsService;

    @Autowired
    @Qualifier(value = "ItemsService")
    public void setItemsService(ItemsService is) {
        this.itemsService = is;
    }

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
    public String searchByAuthor(HttpSession session, Model model, @PathVariable("author") String author, Principal principal) {

        Categories rootCategory = this.categoriesService.getRootCategory();
        model.addAttribute("rootCategory", rootCategory);

        //check whether the somebody is logged in or not
        model.addAttribute("checkprincipal", principal);

        List<Items> listItems = this.paramsService.listItemsByParam(author);
        model.addAttribute("listItems",listItems);

        List<String> listAuthors = this.paramsService.listAuthors();
        model.addAttribute("listAuthors",listAuthors);

        List<String> listLanguages = this.paramsService.listLanguages();
        model.addAttribute("listLanguages",listLanguages);

        List<String> listFormats = this.paramsService.listFormats();
        model.addAttribute("listFormats",listFormats);

        return "catalog";
    }


    @RequestMapping(value = "/searchbylanguage/{language}", method = RequestMethod.GET)
    public String searchByLanguage(HttpSession session, Model model, @PathVariable("language") String language, Principal principal) {

        //check whether the somebody is logged in or not
        model.addAttribute("checkprincipal", principal);

        Categories rootCategory = this.categoriesService.getRootCategory();
        model.addAttribute("rootCategory", rootCategory);

        List<Items> listItemsByLanguage = this.paramsService.searchItemsByLanguageParam(language);

        model.addAttribute("listItems",listItemsByLanguage);

        List<String> listAuthors = this.paramsService.listAuthors();
        model.addAttribute("listAuthors",listAuthors);

        List<String> listLanguages = this.paramsService.listLanguages();
        model.addAttribute("listLanguages",listLanguages);

        List<String> listFormats = this.paramsService.listFormats();
        model.addAttribute("listFormats",listFormats);

        return "catalog";
    }

    @RequestMapping(value = "/searchbyformat/{format}", method = RequestMethod.GET)
    public String searchByFormat(HttpSession session, Model model, @PathVariable("format") String format, Principal principal) {

        //check whether the somebody is logged in or not
        model.addAttribute("checkprincipal", principal);

        Categories rootCategory = this.categoriesService.getRootCategory();
        model.addAttribute("rootCategory", rootCategory);

        List<Items> listItemsByFormat = this.paramsService.searchItemsByFormatParam(format);

        model.addAttribute("listItems",listItemsByFormat);

        List<String> listAuthors = this.paramsService.listAuthors();
        model.addAttribute("listAuthors",listAuthors);

        List<String> listLanguages = this.paramsService.listLanguages();
        model.addAttribute("listLanguages",listLanguages);

        List<String> listFormats = this.paramsService.listFormats();
        model.addAttribute("listFormats",listFormats);

        return "catalog";
    }
}
