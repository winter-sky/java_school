package mainpackage.service;

import mainpackage.controller.StatisticsController;
import mainpackage.dto.ClientStatDTO;
import mainpackage.dto.ItemDTO;
import mainpackage.dto.ItemStatDTO;
import mainpackage.model.Items;
import mainpackage.model.OrderItems;
import mainpackage.model.Orders;
import mainpackage.util.DTOUtil;
import mainpackage.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service("StatisticsService")
@Transactional
public class StatisticsServiceImpl implements StatisticsService {
    /** */
    private static final Logger log = LoggerFactory.getLogger(StatisticsServiceImpl.class);

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

    private OrdersService ordersService;

    private ItemsService itemsService;

    @Autowired
    public void setOrdersService(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @Autowired
    public void setItemsService(ItemsService itemsService) {
        this.itemsService = itemsService;
    }

    @Override
    public List<ClientStatDTO> getTopClients(int count) {
        // TODO: need to be optimized.
        List<Orders> allOrders = ordersService.getAllOrders();

        if (allOrders != null) {
            log.info("All orders read [count=" + allOrders.size() + ']');

            // Client ID to Client DTO map.
            Map<Integer, ClientStatDTO> clientStat = new HashMap<>();

            for (Orders order : allOrders) {
                List<OrderItems> orderItems = order.getOrderItems();

                ClientStatDTO clientStatDTO = clientStat.computeIfAbsent(order.getClient().getClientId(),
                        i -> new ClientStatDTO());

                clientStatDTO.setClient(DTOUtil.toDTO(order.getClient()));

                // Iterate by order items.
                for (OrderItems orderItem : orderItems) {
                    // Get item quantity.
                    Integer qty = orderItem.getItemQuantity();

                    // Get item price.
                    double price = orderItem.getItem().getPrice();

                    // Get amount of money for the item.
                    // TODO: 2 to constants or config
                    double amount = Util.round(price * qty, 2);

                    clientStatDTO.setAmount(clientStatDTO.getAmount() + amount);
                    clientStatDTO.setItemsCount(clientStatDTO.getItemsCount() + qty);
                }

                clientStatDTO.setOrdersCount(clientStatDTO.getOrdersCount() + 1);
            }

            List<ClientStatDTO> sorted = clientStat.values().stream().sorted((o1, o2) -> {
                // Minus because of reversed order.
                return -Double.compare(o1.getAmount(), o2.getAmount());
            }).collect(Collectors.toList());

            log.info("Items sorted by quantity:" + sorted);

            return sorted.subList(0, Math.min(sorted.size(), count));
        }
        else {
            log.info("No orders found.");

            return new ArrayList<>();
        }
    }

    @Override
    public List<ItemStatDTO> getTopItems(int count) {
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

            // Get subset of required size
            List<Integer> topItemsIds = sorted.stream().map(i -> i.itemId).limit(count).collect(Collectors.toList());

            log.info("Top items found:" + topItemsIds);

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

            return dtos;
        }
        else {
            log.info("No orders found.");

            return new ArrayList<>();
        }
    }
}

