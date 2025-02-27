package dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas;

import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.aggregate.BakingOfPizzas;
import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.aggregate.order.OrderReference;
import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.event.PizzasBaked;
import java.util.Optional;

public interface BakingOfPizzaRepository {

    Optional<BakingOfPizzas> findBakingOfPizzas(OrderReference orderReference);

    void sendEvent(PizzasBaked pizzasBaked);

    void save(PizzasBaked pizzasBaked);
}
