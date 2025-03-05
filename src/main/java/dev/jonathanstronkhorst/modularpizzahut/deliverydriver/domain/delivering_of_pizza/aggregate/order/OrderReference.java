package dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.aggregate.order;


import java.util.UUID;

public record OrderReference(UUID orderReference) {
    public static OrderReference of(UUID orderReference) {
        return new OrderReference(orderReference);
    }
}
