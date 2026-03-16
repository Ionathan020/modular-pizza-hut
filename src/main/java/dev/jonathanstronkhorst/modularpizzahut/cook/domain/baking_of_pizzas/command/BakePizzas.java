package dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.command;

import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.aggregate.order.OrderReference;
import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.aggregate.order.OrderStatus;

public class BakePizzas {
    private final OrderReference orderReference;
    private final OrderStatus orderStatus;

    private BakePizzas(OrderReference orderReference, OrderStatus orderStatus) {
        this.orderReference = orderReference;
        this.orderStatus = orderStatus;
    }

    public static BakePizzas of(OrderReference orderReference, OrderStatus orderStatus) {
        return new BakePizzas(orderReference, orderStatus);
    }

    public OrderReference getOrderReference() {
        return orderReference;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }
}
