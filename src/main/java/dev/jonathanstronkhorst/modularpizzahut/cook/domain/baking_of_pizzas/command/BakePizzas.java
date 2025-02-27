package dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.command;

import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.aggregate.order.OrderReference;
import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.aggregate.order.OrderStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(staticName = "of")
public class BakePizzas {
    private final OrderReference orderReference;
    private final OrderStatus orderStatus;
}
