package dev.jonathanstronkhorst.modularpizzahut.handout.domain.handout_of_pizza.command;

import dev.jonathanstronkhorst.modularpizzahut.handout.domain.handout_of_pizza.aggregate.order.OrderReference;

public class HandoutPizza {
    private final OrderReference orderReference;

    private HandoutPizza(OrderReference orderReference) {
        this.orderReference = orderReference;
    }

    public static HandoutPizza of(OrderReference orderReference) {
        return new HandoutPizza(orderReference);
    }

    public OrderReference getOrderReference() {
        return orderReference;
    }
}
