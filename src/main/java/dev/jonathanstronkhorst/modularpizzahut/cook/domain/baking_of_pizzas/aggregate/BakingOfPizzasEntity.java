package dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.aggregate;

import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.aggregate.order.IsDeliveryOrder;
import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.aggregate.order.OrderReference;
import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.aggregate.pizza.Pizza;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
class BakingOfPizzasEntity {
    private final OrderReference orderReference;
    private final IsDeliveryOrder isDeliveryOrder;
    private final List<Pizza> pizzas;

    public static BakingOfPizzasEntity of(OrderReference orderReference,
                                          IsDeliveryOrder isDeliveryOrder,
                                          List<Pizza> pizzas) {
        return new BakingOfPizzasEntity(orderReference, isDeliveryOrder, pizzas);
    }
}
