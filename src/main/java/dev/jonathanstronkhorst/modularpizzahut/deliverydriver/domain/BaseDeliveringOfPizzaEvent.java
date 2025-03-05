package dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain;

import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.DeliveringOfPizzaRepository;

public interface BaseDeliveringOfPizzaEvent {
    void publish(DeliveringOfPizzaRepository bakingOfPizzaRepository);
    void save(DeliveringOfPizzaRepository bakingOfPizzaRepository);
}
