package dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.command;

import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.aggregate.order.OrderReference;

public class DeliverPizza {
    private final OrderReference orderReference;

    private DeliverPizza(OrderReference orderReference) {
        this.orderReference = orderReference;
    }

    public static DeliverPizza of(OrderReference orderReference) {
        return new DeliverPizza(orderReference);
    }

    public OrderReference getOrderReference() {
        return orderReference;
    }
}
