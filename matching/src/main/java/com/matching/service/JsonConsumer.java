package com.matching.service;


import com.matching.comparator.BuyPriceComparator;
import com.matching.comparator.SellPriceComparator;
import com.matching.model.Buy;
import com.matching.model.Sell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@Component
public class JsonConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonConsumer.class);

//    private static final BigDecimal TOLERANCE = new BigDecimal("0.01");
//    TreeMap<Buy, Integer> buyMap = new TreeMap<>(new BuyPriceComparator());
//    TreeMap<Sell, Integer> sellMap = new TreeMap<>(new SellPriceComparator());

//    @RabbitListener(queues = "buy-queue")
//    public void consumeMessageBuy(Buy buy) {
//        LOGGER.info("Message received buy --> {}", buy);
//        int key = buy.getId();
//        buyMap.put(buy, buy.getId());
//
////        comparePrices(buy.getPrice(), true);
//        System.out.println("sell descending  order");
//        for (Buy buy1 : buyMap.keySet()) {
//            System.out.println("Buy ID: " + buy1.getId() + ", Price: " + buy1.getPrice());
//        }
//    }
//
//    @RabbitListener(queues = "sell-queue")
//    public void consumeMessageSell(Sell sell) {
//        LOGGER.info("Message received sell --> {}", sell);
////        comparePrices(sell.getPrice(), false);
//
//        sellMap.put(sell, sell.getId());
//
//        System.out.println("sell Ascending order");
//        for (Sell sell1 : sellMap.keySet()) {
//            System.out.println("sell ID: " + sell1.getId() + ", Price: " + sell1.getPrice());
//        }
//    }


    private Map<String, LinkedList<Buy>> buyOrdersBySymbol = new TreeMap<>();

    private Map<String, LinkedList<Sell>> sellOrdersBySymbol = new TreeMap<>();

    @RabbitListener(queues = "buy-queue")
    public void consumeMessageBuy(Buy buy) {
        LOGGER.info("Message received buy --> {}", buy);

        String symbol = buy.getSymbol();
        LinkedList<Buy> buyOrders = buyOrdersBySymbol.computeIfAbsent(symbol, k -> new LinkedList<>());

        Optional<Buy> existingOrder = buyOrders.stream()
                .filter(order -> order.getSymbol().equals(buy.getSymbol()))
                .findFirst();

        if (existingOrder.isPresent()) {
            Buy orderToUpdate = existingOrder.get();
            orderToUpdate.setOrderQty(orderToUpdate.getOrderQty() + buy.getOrderQty());
            orderToUpdate.setPrice(buy.getPrice().add(orderToUpdate.getPrice()));
            Collections.sort(buyOrders, Comparator.comparing(Buy::getPrice));
        } else {
            buyOrders.add(buy);
            Collections.sort(buyOrders, Comparator.comparing(Buy::getPrice));
        }

        System.out.println("Symbol: " + symbol);
        for (Buy buyOrder : buyOrders) {
            System.out.println(buyOrder);
            }
        }



    @RabbitListener(queues = "sell-queue")
    public void consumeMessageSell(Sell sell) {

        LOGGER.info("Message received buy --> {}", sell);

        String symbol = sell.getSymbol();
        LinkedList<Sell> sellOrders = sellOrdersBySymbol.computeIfAbsent(symbol, k -> new LinkedList<>());

        Optional<Sell> existingOrder = sellOrders.stream()
                .filter(order -> order.getSymbol().equals(sell.getSymbol()))
                .findFirst();

        if (existingOrder.isPresent()) {
            Sell orderToUpdate = existingOrder.get();
            orderToUpdate.setOrderQty(orderToUpdate.getOrderQty() + sell.getOrderQty());
            orderToUpdate.setPrice(sell.getPrice().add(orderToUpdate.getPrice()));
            Collections.sort(sellOrders, Comparator.comparing(Sell::getPrice));
        } else {
            sellOrders.add(sell);
            Collections.sort(sellOrders, Comparator.comparing(Sell::getPrice));
        }

        System.out.println("Symbol: " + symbol);
        for (Sell sellOrder : sellOrders) {
            System.out.println(sellOrder);
        }
    }
    }





