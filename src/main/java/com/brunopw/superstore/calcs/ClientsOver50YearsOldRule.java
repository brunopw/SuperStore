package com.brunopw.superstore.calcs;

import com.brunopw.superstore.Order;

import java.time.LocalDate;

public class ClientsOver50YearsOldRule implements DiscountRule {

    @Override
    public boolean apply(Order order) {
        return order.getBuyer().getBirthday().isBefore(LocalDate.now().minusYears(50));
    }

    @Override
    public Double calculateDiscount(Order order) {
        return 10d;
    }
}
