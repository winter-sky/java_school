package mainpackage.controller;

import mainpackage.dto.ClientStatDTO;
import mainpackage.dto.ItemDTO;
import mainpackage.dto.ItemStatDTO;
import mainpackage.dto.RevenueStatDTO;
import mainpackage.model.Items;
import mainpackage.model.OrderItems;
import mainpackage.model.Orders;
import mainpackage.service.ItemsService;
import mainpackage.service.OrdersService;
import mainpackage.service.StatisticsService;
import mainpackage.util.DTOUtil;
import mainpackage.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

@Controller
public class StatisticsController {
    /** */
    private static final Logger log = LoggerFactory.getLogger(StatisticsController.class);

    // TODO: as request parameter.
    private static final int TOP_COUNT = 10;

    private StatisticsService statService;

    @Autowired
    public void setStatService(StatisticsService statService) {
        this.statService = statService;
    }

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
