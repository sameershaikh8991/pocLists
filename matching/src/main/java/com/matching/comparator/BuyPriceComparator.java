package com.matching.comparator;
//

import com.matching.model.Buy;

import java.util.Comparator;


public class BuyPriceComparator implements Comparator<Buy> {
    @Override
    public int compare(Buy buy1, Buy buy2) {
        return buy2.getPrice().compareTo(buy1.getPrice());
    }
}