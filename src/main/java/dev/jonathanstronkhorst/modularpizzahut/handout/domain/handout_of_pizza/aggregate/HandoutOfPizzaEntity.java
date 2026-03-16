package dev.jonathanstronkhorst.modularpizzahut.handout.domain.handout_of_pizza.aggregate;

import dev.jonathanstronkhorst.modularpizzahut.handout.domain.handout_of_pizza.aggregate.order.OrderReference;

class HandoutOfPizzaEntity {
    private final OrderReference orderReference;

    public HandoutOfPizzaEntity(OrderReference orderReference) {
        this.orderReference = orderReference;
    }

    public OrderReference getOrderReference() {
        return orderReference;
    }

    public static HandoutOfPizzaEntity of(OrderReference orderReference) {
        return new HandoutOfPizzaEntity(orderReference);
    }
}
