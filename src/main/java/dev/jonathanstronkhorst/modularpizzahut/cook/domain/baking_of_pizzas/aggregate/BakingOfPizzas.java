package dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.aggregate;

import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.aggregate.order.IsDeliveryOrder;
import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.aggregate.order.OrderReference;
import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.aggregate.order.OrderStatus;
import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.aggregate.pizza.BakedPizzaResult;
import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.aggregate.pizza.Pizza;
import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.command.BakePizzas;
import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.event.PizzasBaked;
import java.util.List;

public class BakingOfPizzas {
    private final BakingOfPizzasEntity bakingOfPizzasEntity;

    public BakingOfPizzas(BakingOfPizzasEntity bakingOfPizzasEntity) {
        this.bakingOfPizzasEntity = bakingOfPizzasEntity;
    }

    public static BakingOfPizzas of(OrderReference orderReference,
                                   IsDeliveryOrder isDeliveryOrder,
                                   List<Pizza> pizzas) {
        return new BakingOfPizzas(BakingOfPizzasEntity.of(orderReference, isDeliveryOrder, pizzas));
    }

    public BakedPizzaResult handleCommand(BakePizzas bakePizzas) {
        return BakedPizzaResult.of(bakePizzas.getOrderReference(),
                PizzasBaked.of(bakingOfPizzasEntity.getOrderReference(),
                        OrderStatus.BAKED,
                        bakingOfPizzasEntity.getIsDeliveryOrder(),
                        bakingOfPizzasEntity.getPizzas()),
                OrderStatus.BAKED);
    }
}
