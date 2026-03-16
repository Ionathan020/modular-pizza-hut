package dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.aggregate.pizza;

import dev.jonathanstronkhorst.modularpizzahut.cook.domain.BasePizzaBakedEvent;
import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.aggregate.order.OrderReference;
import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.aggregate.order.OrderStatus;

public class BakedPizzaResult {
    private final OrderReference orderReference;
    private final BasePizzaBakedEvent event;
    private final OrderStatus orderStatus;

    public BakedPizzaResult(OrderReference orderReference, BasePizzaBakedEvent event, OrderStatus orderStatus) {
        this.orderReference = orderReference;
        this.event = event;
        this.orderStatus = orderStatus;
    }

    public OrderReference getOrderReference() {
        return orderReference;
    }

    public BasePizzaBakedEvent getEvent() {
        return event;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public static BakedPizzaResult of(OrderReference orderReference, BasePizzaBakedEvent event, OrderStatus orderStatus) {
        return new BakedPizzaResult(orderReference, event, orderStatus);
    }

    public static BakedPizzaResult of(OrderReference orderReference, OrderStatus orderStatus) {
        return new BakedPizzaResult(orderReference, null, orderStatus);
    }
}
