package dev.jonathanstronkhorst.modularpizzahut.cashier.domain;

import dev.jonathanstronkhorst.modularpizzahut.cashier.domain.order_of_pizzas.OrderOfPizzaRepository;

public interface BasePizzaOrderEvent {

    void publish(OrderOfPizzaRepository orderOfPizzaRepository);
    void save(OrderOfPizzaRepository orderOfPizzaRepository);
}
