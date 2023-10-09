package com.matching.comparator;

import com.matching.model.Buy;
import com.matching.model.Sell;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Map;

public class SellPriceComparator implements  Comparator<Sell> {
//    private Map<Integer, Sell> mapToSort;
//
//    public SellPriceComparator(Map<Integer, Sell> mapToSort) {
//        this.mapToSort = mapToSort;
//    }
//
//    @Override
//    public int compare(Integer key1, Integer key2) {
//        BigDecimal price1 = mapToSort.get(key1).getPrice();
//        BigDecimal price2 = mapToSort.get(key2).getPrice();
//        return price1.compareTo(price2);
//    }

    @Override
    public int compare(Sell buy1, Sell buy2) {
        return buy1.getPrice().compareTo(buy2.getPrice());
    }

}