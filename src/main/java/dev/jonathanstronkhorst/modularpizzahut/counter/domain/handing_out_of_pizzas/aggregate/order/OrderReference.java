package dev.jonathanstronkhorst.modularpizzahut.counter.domain.handing_out_of_pizzas.aggregate.order;

import java.util.UUID;

public record OrderReference(UUID orderReference) {
    public static OrderReference of(UUID orderReference) {
        return new OrderReference(orderReference);
    }
}