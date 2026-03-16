package dev.jonathanstronkhorst.modularpizzahut.handout.domain;

import dev.jonathanstronkhorst.modularpizzahut.handout.domain.handout_of_pizza.HandoutOfPizzaRepository;

public interface BaseHandoutOfPizzaEvent {
    void publish(HandoutOfPizzaRepository handoutOfPizzaRepository);
    void save(HandoutOfPizzaRepository handoutOfPizzaRepository);
}
