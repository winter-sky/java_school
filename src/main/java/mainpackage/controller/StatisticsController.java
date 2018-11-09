package mainpackage.controller;

import mainpackage.dto.ItemDTO;
import mainpackage.dto.ItemStatDTO;
import mainpackage.model.Items;
import mainpackage.model.OrderItems;
import mainpackage.model.Orders;
import mainpackage.service.ItemsService;
import mainpackage.service.OrdersService;
import mainpackage.util.DTOUtil;
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

    private ItemsService itemsService;

    private OrdersService ordersService;

    private class ItemQty implements Comparable<ItemQty> {
        ItemQty(int itemId, int qty) {
            this.itemId = itemId;
            this.qty = qty;
        }

        private int itemId;

        private int qty;

        public int getItemId() {
            return itemId;
        }

        public int getQty() {
            return qty;
        }

        @Override
        public int compareTo(ItemQty o) {
            // Minus because of descending order.
            return -Integer.compare(qty, o.qty);
        }

        @Override
        public String toString() {
            return "ItemQty{" +
                "itemId=" + itemId +
                ", qty=" + qty +
                '}';
        }
    }

    @Autowired
    public void setItemsService(ItemsService itemsService) {
        this.itemsService = itemsService;
    }

    @Autowired
    public void setOrdersService(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @RequestMapping(value = "/statistics", method = RequestMethod.GET)
    public String statistics(Model model) {
        return "statistics";
    }

        @RequestMapping(value = "/topitems", method = RequestMethod.GET)
    public String topItems(Model model) {
        // TODO: need to be optimized.
        List<Orders> allOrders = ordersService.getAllOrders();

        if (allOrders != null) {
            log.info("All orders read [count=" + allOrders.size() + ']');

            Map<Integer, Integer> item2Quantity = new HashMap<>();

            for (Orders order : allOrders) {
                List<OrderItems> orderItems = order.getOrderItems();

                for (OrderItems orderItem : orderItems) {
                    Integer itemId = orderItem.getItem().getItemId();

                    Integer qty = orderItem.getItemQuantity();

                    item2Quantity.merge(itemId, qty, (a, b) -> a + b);
                }
            }

            log.info("Item2quantity created:" + item2Quantity);

            Set<ItemQty> sorted = new TreeSet<>(item2Quantity.entrySet().stream().map(e -> new ItemQty(e.getKey(),
                e.getValue())).collect(Collectors.toSet()));

            log.info("Items sorted by quantity:" + sorted);

            final AtomicInteger count = new AtomicInteger();

            // Get subset of required size
            Integer[] topItemsIds = sorted.stream().map(i -> i.itemId).filter(integer -> {
                int cnt = count.incrementAndGet();

                return cnt < TOP_COUNT;
            }).collect(Collectors.toList()).toArray(new Integer[Math.min(count.get(), TOP_COUNT)]);

            log.info("Top items found:" + Arrays.toString(topItemsIds));

            final Map<Integer, Items> topItems = itemsService.findItemsByIds(topItemsIds).stream().collect(
                Collectors.toMap(Items::getItemId, Function.identity()));

            List<ItemStatDTO> dtos = sorted.stream().map(iq -> {
                Items item = topItems.get(iq.itemId);

                ItemDTO dto = DTOUtil.toDTO(item);

                ItemStatDTO statDto = new ItemStatDTO();

                statDto.setItem(dto);
                statDto.setQuantitiesSold(iq.qty);

                return statDto;
            }).collect(Collectors.toList());

            model.addAttribute("listItems", dtos);
        }
        else {
            log.info("No orders found.");

            model.addAttribute("listItems", new ArrayList<Items>());
        }

        return "topitems";
    }
}
