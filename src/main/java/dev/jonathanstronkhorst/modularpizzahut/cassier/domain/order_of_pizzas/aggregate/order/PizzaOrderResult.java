package dev.jonathanstronkhorst.modularpizzahut.cassier.domain.order_of_pizzas.aggregate.order;

import dev.jonathanstronkhorst.modularpizzahut.cassier.domain.BasePizzaOrderEvent;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PizzaOrderResult {
    private final OrderReference orderReference;
    private final BasePizzaOrderEvent event;
    private final OrderStatus orderStatus;

    public static PizzaOrderResult of(OrderReference orderReference, BasePizzaOrderEvent event, OrderStatus orderStatus) {
        return new PizzaOrderResult(orderReference, event, orderStatus);
    }

    public static PizzaOrderResult of(OrderReference orderReference, OrderStatus orderStatus) {
        return new PizzaOrderResult(orderReference, null, orderStatus);
    }
}
