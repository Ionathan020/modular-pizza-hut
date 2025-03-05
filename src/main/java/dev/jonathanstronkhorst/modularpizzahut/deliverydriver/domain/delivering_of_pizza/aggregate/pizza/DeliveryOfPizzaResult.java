package dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.aggregate.pizza;

import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.BaseDeliveringOfPizzaEvent;
import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.aggregate.order.OrderReference;
import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.aggregate.order.OrderStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DeliveryOfPizzaResult {
    private final OrderReference orderReference;
    private final BaseDeliveringOfPizzaEvent event;
    private final OrderStatus orderStatus;

    public static DeliveryOfPizzaResult of(OrderReference orderReference, BaseDeliveringOfPizzaEvent event, OrderStatus orderStatus) {
        return new DeliveryOfPizzaResult(orderReference, event, orderStatus);
    }

    public static DeliveryOfPizzaResult of(OrderReference orderReference, OrderStatus orderStatus) {
        return new DeliveryOfPizzaResult(orderReference, null, orderStatus);
    }
}
