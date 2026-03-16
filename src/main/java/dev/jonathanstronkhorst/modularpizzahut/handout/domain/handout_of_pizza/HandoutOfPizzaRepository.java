package dev.jonathanstronkhorst.modularpizzahut.handout.domain.handout_of_pizza;

import dev.jonathanstronkhorst.modularpizzahut.handout.domain.handout_of_pizza.aggregate.HandoutOfPizza;
import dev.jonathanstronkhorst.modularpizzahut.handout.domain.handout_of_pizza.aggregate.order.OrderReference;
import dev.jonathanstronkhorst.modularpizzahut.handout.domain.handout_of_pizza.event.PizzaHandedOut;

import java.util.Optional;

public interface HandoutOfPizzaRepository {
    Optional<HandoutOfPizza> findHandoutOfPizza(OrderReference orderReference);
    void sendEvent(PizzaHandedOut pizzaHandedOut);
    void save(PizzaHandedOut pizzaHandedOut);
}
