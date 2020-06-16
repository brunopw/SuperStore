package com.brunopw.superstore.calcs;

import java.util.Arrays;
import java.util.List;

import com.brunopw.superstore.Order;

public class DiscountRuleResolver {

    private static DiscountRuleResolver discountRuleResolver;
    private List<DiscountRule> discountRuleList;

    private DiscountRuleResolver() {
        this.discountRuleList = Arrays.asList(new ClientsOver70YearsOldRule(), new ClientsOver50YearsOldRule(), new ClientsLivingInDublinRule());
    }

    public static DiscountRuleResolver getInstance() {
        if (discountRuleResolver == null) {
            discountRuleResolver = new DiscountRuleResolver();
        }
        return discountRuleResolver;
    }

    public Double calculateDicount(Order order) {
        return discountRuleList.stream().filter(discountRule -> discountRule.apply(order)).findAny().orElse(new DefaultRule()).calculateDiscount(order);
    }
}
