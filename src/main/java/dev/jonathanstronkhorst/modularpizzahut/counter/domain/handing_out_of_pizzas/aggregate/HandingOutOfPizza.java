package dev.jonathanstronkhorst.modularpizzahut.counter.domain.handing_out_of_pizzas.aggregate;

import dev.jonathanstronkhorst.modularpizzahut.counter.domain.handing_out_of_pizzas.aggregate.order.OrderReference;
import dev.jonathanstronkhorst.modularpizzahut.counter.domain.handing_out_of_pizzas.command.HandOutPizza;
import dev.jonathanstronkhorst.modularpizzahut.counter.domain.handing_out_of_pizzas.event.PizzaHandedOut;

public class HandingOutOfPizza {
    private final OrderReference orderReference;

    private HandingOutOfPizza(OrderReference orderReference) {
        this.orderReference = orderReference;
    }

    public PizzaHandedOut handleCommand(HandOutPizza handOutPizza) {
        return PizzaHandedOut.of(orderReference);
    }

    public static HandingOutOfPizza of(OrderReference orderReference) {
        return new HandingOutOfPizza(orderReference);
    }
}