package dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.aggregate.order;

import java.util.UUID;

public record OrderReference(UUID orderReference) {
    public static OrderReference of(UUID orderReference) {
        return new OrderReference(orderReference);
    }
}
