package dev.jonathanstronkhorst.modularpizzahut.counter.domain.handing_out_of_pizzas;

import dev.jonathanstronkhorst.modularpizzahut.counter.domain.handing_out_of_pizzas.aggregate.HandingOutOfPizza;
import dev.jonathanstronkhorst.modularpizzahut.counter.domain.handing_out_of_pizzas.aggregate.order.OrderReference;
import dev.jonathanstronkhorst.modularpizzahut.counter.domain.handing_out_of_pizzas.event.PizzaHandedOut;
import java.util.Optional;

public interface HandingOutOfPizzaRepository {
    Optional<HandingOutOfPizza> findHandingOutOfPizza(OrderReference orderReference);

    void saveInitialOrder(OrderReference orderReference);

    void save(PizzaHandedOut pizzaHandedOut);

    void sendEvent(PizzaHandedOut pizzaHandedOut);
}