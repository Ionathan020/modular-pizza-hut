package dev.jonathanstronkhorst.modularpizzahut.cashier.domain.order_of_pizzas.aggregate.order;

public record IsDeliveryOrder(boolean isDeliveryOrder) {
    public static IsDeliveryOrder of(boolean isDeliveryOrder) {
        return new IsDeliveryOrder(isDeliveryOrder);
    }
}
