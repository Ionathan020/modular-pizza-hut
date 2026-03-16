package dev.jonathanstronkhorst.modularpizzahut.handout.domain.handout_of_pizza.aggregate.pizza;

import dev.jonathanstronkhorst.modularpizzahut.handout.domain.BaseHandoutOfPizzaEvent;
import dev.jonathanstronkhorst.modularpizzahut.handout.domain.handout_of_pizza.aggregate.order.OrderReference;
import dev.jonathanstronkhorst.modularpizzahut.handout.domain.handout_of_pizza.aggregate.order.OrderStatus;

public class HandoutOfPizzaResult {
    private final OrderReference orderReference;
    private final BaseHandoutOfPizzaEvent event;
    private final OrderStatus orderStatus;

    public HandoutOfPizzaResult(OrderReference orderReference, BaseHandoutOfPizzaEvent event, OrderStatus orderStatus) {
        this.orderReference = orderReference;
        this.event = event;
        this.orderStatus = orderStatus;
    }

    public OrderReference getOrderReference() {
        return orderReference;
    }

    public BaseHandoutOfPizzaEvent getEvent() {
        return event;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public static HandoutOfPizzaResult of(OrderReference orderReference, BaseHandoutOfPizzaEvent event, OrderStatus orderStatus) {
        return new HandoutOfPizzaResult(orderReference, event, orderStatus);
    }

    public static HandoutOfPizzaResult of(OrderReference orderReference, OrderStatus orderStatus) {
        return new HandoutOfPizzaResult(orderReference, null, orderStatus);
    }
}
