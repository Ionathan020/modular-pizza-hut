package dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.aggregate;

import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.aggregate.order.IsDeliveryOrder;
import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.aggregate.order.OrderReference;
import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.aggregate.pizza.Pizza;
import java.util.List;

class BakingOfPizzasEntity {
    private final OrderReference orderReference;
    private final IsDeliveryOrder isDeliveryOrder;
    private final List<Pizza> pizzas;

    public BakingOfPizzasEntity(OrderReference orderReference,
                                IsDeliveryOrder isDeliveryOrder,
                                List<Pizza> pizzas) {
        this.orderReference = orderReference;
        this.isDeliveryOrder = isDeliveryOrder;
        this.pizzas = pizzas;
    }

    public OrderReference getOrderReference() {
        return orderReference;
    }

    public IsDeliveryOrder getIsDeliveryOrder() {
        return isDeliveryOrder;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public static BakingOfPizzasEntity of(OrderReference orderReference,
                                          IsDeliveryOrder isDeliveryOrder,
                                          List<Pizza> pizzas) {
        return new BakingOfPizzasEntity(orderReference, isDeliveryOrder, pizzas);
    }
}
