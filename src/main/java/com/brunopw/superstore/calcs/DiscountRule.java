package com.brunopw.superstore.calcs;

import com.brunopw.superstore.Order;

public interface DiscountRule {

    boolean apply(Order order);
    Double calculateDiscount(Order order);

}
