package dev.jonathanstronkhorst.modularpizzahut.cook.domain;

import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.BakingOfPizzaRepository;

public interface BasePizzaBakedEvent {
    void publish(BakingOfPizzaRepository bakingOfPizzaRepository);
    void save(BakingOfPizzaRepository bakingOfPizzaRepository);
}
