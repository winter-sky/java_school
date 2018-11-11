package mainpackage.controller;

import mainpackage.dto.ClientStatDTO;
import mainpackage.dto.ItemStatDTO;
import mainpackage.dto.RevenueStatDTO;
import mainpackage.service.OrdersService;
import mainpackage.service.StatisticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private OrdersService ordersService;

    @Autowired
    @Qualifier(value = "OrdersService")
    public void setOrdersService(OrdersService os) {
        this.ordersService = os;
    }

    private StatisticsService statService;

    @Autowired
    public void setStatService(StatisticsService statService) {
        this.statService = statService;
    }

    @RequestMapping(value = "/adminpage", method = RequestMethod.GET)
    public String adminPage() {

        return "admin_page";
    }

    /** */
    private static final Logger log = LoggerFactory.getLogger(AdminController.class);

    // TODO: as request parameter.
    private static final int TOP_COUNT = 10;

    @RequestMapping(value = "/statistics", method = RequestMethod.GET)
    public String statistics(Model model) {
        return "statistics";
    }

    @RequestMapping(value = "/topclients", method = RequestMethod.GET)
    public String topClients(Model model) {
        List<ClientStatDTO> topClients = statService.getTopClients(TOP_COUNT);

        model.addAttribute("listClients", topClients);

        return "topclients";
    }

    /**
     * Gets top clients by summary amount of sales.
     * @param model
     * @return
     */
    @RequestMapping(value = "/topitems", method = RequestMethod.GET)
    public String topItems(Model model) {
        List<ItemStatDTO> dtos = statService.getTopItems(TOP_COUNT);

        model.addAttribute("listItems", dtos);

        return "topitems";
    }

    /**
     * Gets top clients by summary amount of sales.
     * @param model
     * @return
     */
    @RequestMapping(value = "/monthrevenue", method = RequestMethod.GET)
    public String monethRevenue(Model model) {
        RevenueStatDTO dto = statService.getMonthlyStat(System.currentTimeMillis());

        model.addAttribute("revenueStat", dto);

        return "revenue";
    }

    @RequestMapping(value = "/weekrevenue", method = RequestMethod.GET)
    public String weekRevenue(Model model) {
        RevenueStatDTO dto = statService.getWeeklyStat(System.currentTimeMillis());

        model.addAttribute("revenueStat", dto);

        return "revenue";
    }
}
