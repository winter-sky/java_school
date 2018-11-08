package mainpackage.controller;

import mainpackage.model.Items;
import mainpackage.service.ItemsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ItemsEditController {
    /** */
    private static final Logger log = LoggerFactory.getLogger(ItemsEditController.class);

    ItemsService itemsService;

    @Autowired
    @Qualifier(value = "ItemsService")
    public void setItemsService(ItemsService itemsService) {
        this.itemsService = itemsService;
    }


    @RequestMapping(value = "/additemform", method = RequestMethod.GET)
    public String addItemForm(Model model) {
        model.addAttribute("item", new Items());

        return "additem";
    }

    @RequestMapping(value = "/additem", method = RequestMethod.POST)
    public String submit(@ModelAttribute("item") Items item, BindingResult result, RedirectAttributes attributes) {
        log.info("Adding item: " + item);

        itemsService.addItem(item);

        return "list_items";
    }

//    @RequestMapping(value = "/additem", method = RequestMethod.POST)
//    public String submit(@RequestBody Items item) {
//        itemsService.addItem(item);
//
//        return "list_items";
//    }
}
