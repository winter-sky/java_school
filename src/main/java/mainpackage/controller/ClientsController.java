package mainpackage.controller;

import mainpackage.model.ClientAddresses;
import mainpackage.model.Clients;
import mainpackage.service.ClientsService;
import mainpackage.type.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class ClientsController {

    private ClientsService clientsService;

    @Autowired
    @Qualifier(value="ClientsService")
    public void setClientsService(ClientsService cs){
        this.clientsService = cs;
    }

    @RequestMapping(value="/selectaddress", method = RequestMethod.GET)
    public String selectOrderStatus (Model model, Principal principal, @RequestParam("address")ClientAddresses address){
        String login = principal.getName();
        Clients client = this.clientsService.findClientByLogin(login);
        model.addAttribute("client",client);
        return  "select_address";
    }

    @RequestMapping(value="/searchclientbylogin/{clientLogin}",method = RequestMethod.GET)
    public String searchClientByLogin(Model model, @PathVariable("clientLogin") String clientLogin){
        Clients client = this.clientsService.findClientByLogin(clientLogin);
        model.addAttribute("client",client);
        return "client_by_login";
    }

    @RequestMapping(value = "/editprofile", method = RequestMethod.GET)//
    public String listCustomers(Model model) {
        model.addAttribute("client", new Clients());
        return "edit_client";
    }

    @RequestMapping(value= "/addclient", method = RequestMethod.POST)
    public String addClient(@ModelAttribute("client") Clients c){
        this.clientsService.addClient(c);
        if(c.getClientId() == 0){
            //new client, add it
            this.clientsService.addClient(c);
        }else{
            //existing client, call update
            this.clientsService.updateClient(c);
        }
        return "edit_client";
    }

    @RequestMapping("/edit/{clientId}")
    public String editClient(@PathVariable("clientId") int clientId,Model model){

        model.addAttribute("client", this.clientsService.getClientById(clientId));

        return "edit_client";
    }

    @RequestMapping(value= "/updateclient", method = RequestMethod.POST)
    public String updateClient(@ModelAttribute("client") Clients c,Model model){
        this.clientsService.updateClient(c);

        return "client_by_login";
    }
}
