package dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.event;

import dev.jonathanstronkhorst.modularpizzahut.cook.domain.BasePizzaBakedEvent;
import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.BakingOfPizzaRepository;
import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.aggregate.order.IsDeliveryOrder;
import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.aggregate.order.OrderReference;
import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.aggregate.order.OrderStatus;
import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.aggregate.pizza.Pizza;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(staticName = "of")
public class PizzasBaked implements BasePizzaBakedEvent {
    private final OrderReference orderReference;
    private final OrderStatus orderStatus;
    private final IsDeliveryOrder isDeliveryOrder;
    private final List<Pizza> pizzas;

    @Override
    public void publish(BakingOfPizzaRepository bakingOfPizzaRepository) {
        bakingOfPizzaRepository.sendEvent(this);
    }

    @Override
    public void save(BakingOfPizzaRepository bakingOfPizzaRepository) {
        bakingOfPizzaRepository.save(this);
    }
}
