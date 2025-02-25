package dev.jonathanstronkhorst.modularpizzahut.cassier.domain.order_of_pizzas.aggregate.order;

public record DeliveryAddress(String address) {
    public static DeliveryAddress of(String address) {
        return new DeliveryAddress(address);
    }
}
