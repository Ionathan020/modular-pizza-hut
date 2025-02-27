package dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas;

import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.aggregate.BakingOfPizzas;
import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.aggregate.order.IsDeliveryOrder;
import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.aggregate.order.OrderReference;
import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.aggregate.pizza.Pizza;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BakingOfPizzaProvider {

    BakingOfPizzaRepository bakingOfPizzaRepository;

    public BakingOfPizzas getBakingOfPizzas(OrderReference orderReference, IsDeliveryOrder isDeliveryOrder,
                                            List<Pizza> pizzas) {
        return bakingOfPizzaRepository.findBakingOfPizzas(orderReference)
                .orElse(BakingOfPizzas.of(orderReference, isDeliveryOrder, pizzas));
    }
}
