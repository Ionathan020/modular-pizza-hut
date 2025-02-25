package dev.jonathanstronkhorst.modularpizzahut.cassier.domain;

import dev.jonathanstronkhorst.modularpizzahut.cassier.domain.order_of_pizzas.OrderOfPizzaRepository;

public interface BasePizzaOrderEvent {

    void publish(OrderOfPizzaRepository orderOfPizzaRepository);
    void save(OrderOfPizzaRepository orderOfPizzaRepository);
}
