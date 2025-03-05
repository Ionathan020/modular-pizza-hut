package dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.command;

import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.aggregate.order.OrderReference;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(staticName = "of")
public class DeliverPizza {
    private final OrderReference orderReference;
}
