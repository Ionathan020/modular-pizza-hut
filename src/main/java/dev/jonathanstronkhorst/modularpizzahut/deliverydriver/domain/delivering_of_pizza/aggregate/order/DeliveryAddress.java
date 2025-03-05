package dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.aggregate.order;

public record DeliveryAddress(String address) {
    public static DeliveryAddress of(String address) {
        return new DeliveryAddress(address);
    }
}
