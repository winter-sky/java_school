package mainpackage.controller;

import mainpackage.model.Items;
import mainpackage.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ItemsEditController {
    ItemsService itemsService;

    @Autowired
    @Qualifier(value = "ItemsService")
    public void setItemsService(ItemsService itemsService) {
        this.itemsService = itemsService;
    }


    @RequestMapping(value = "/additem", method = RequestMethod.POST)
    public String submit(@RequestParam("item") Items item, BindingResult result, RedirectAttributes attributes) {
        itemsService.addItem(item);

        return "list_items";
    }
}
