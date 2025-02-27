package dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.aggregate.pizza;

import dev.jonathanstronkhorst.modularpizzahut.cook.domain.BasePizzaBakedEvent;
import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.aggregate.order.OrderReference;
import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.aggregate.order.OrderStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BakedPizzaResult {
    private final OrderReference orderReference;
    private final BasePizzaBakedEvent event;
    private final OrderStatus orderStatus;

    public static BakedPizzaResult of(OrderReference orderReference, BasePizzaBakedEvent event, OrderStatus orderStatus) {
        return new BakedPizzaResult(orderReference, event, orderStatus);
    }

    public static BakedPizzaResult of(OrderReference orderReference, OrderStatus orderStatus) {
        return new BakedPizzaResult(orderReference, null, orderStatus);
    }
}
