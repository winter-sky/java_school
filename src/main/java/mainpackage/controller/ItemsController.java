package mainpackage.controller;

import mainpackage.model.Categories;
import mainpackage.model.Items;
import mainpackage.service.CustomersService;
import mainpackage.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class ItemsController {
    ItemsService itemsService;

    @Autowired
    @Qualifier(value="ItemsService")
    public void setItemsService(ItemsService is){
        this.itemsService = is;
    }

    @RequestMapping(value = "/listitems", method = RequestMethod.GET)
    public String listItems(Model model) {
        model.addAttribute("items", new Items());
        List<Items> list = this.itemsService.listItems();
        model.addAttribute("listItems", list);
        return "list_items";
    }


}
