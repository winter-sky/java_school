package mainpackage.controller;

import mainpackage.model.Clients;
import mainpackage.service.CategoriesService;
import mainpackage.service.ClientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class ClientsController {

    private ClientsService clientsService;

    @Autowired
    @Qualifier(value="ClientsService")
    public void setClientsService(ClientsService cs){
        this.clientsService = cs;
    }


    @RequestMapping(value="/searchclientbylogin/{clientLogin}",method = RequestMethod.GET)
    public String searchClientByLogin(Model model, @PathVariable("clientLogin") String clientLogin){
        System.out.println("Э?");
        Clients client = this.clientsService.findClientByLogin(clientLogin);
        model.addAttribute("client",client);
        return "client_by_login";
    }

    @RequestMapping(value = "/listclients", method = RequestMethod.GET)
    public String listCustomers(Model model) {
        model.addAttribute("client", new Clients());
        return "list_clients";
    }

    @RequestMapping(value= "/addclient", method = RequestMethod.POST)
    public String addClient(@ModelAttribute("client") Clients c){
        System.out.println("ЖЕПА");
        this.clientsService.addClient(c);
        return "list_clients";
    }
}
