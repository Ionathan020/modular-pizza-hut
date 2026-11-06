package dev.jonathanstronkhorst.modularpizzahut.counter.domain.handing_out_of_pizzas.command;

import dev.jonathanstronkhorst.modularpizzahut.counter.domain.handing_out_of_pizzas.aggregate.order.OrderReference;

public record HandOutPizza(OrderReference orderReference) {
    public static HandOutPizza of(OrderReference orderReference) {
        return new HandOutPizza(orderReference);
    }
}