package dev.jonathanstronkhorst.modularpizzahut.counter.domain.handing_out_of_pizzas.event;

import dev.jonathanstronkhorst.modularpizzahut.counter.domain.handing_out_of_pizzas.aggregate.order.OrderReference;
import dev.jonathanstronkhorst.modularpizzahut.counter.domain.handing_out_of_pizzas.HandingOutOfPizzaRepository;

public class PizzaHandedOut {
    private final OrderReference orderReference;

    private PizzaHandedOut(OrderReference orderReference) {
        this.orderReference = orderReference;
    }

    public OrderReference getOrderReference() {
        return orderReference;
    }

    public void publish(HandingOutOfPizzaRepository handingOutOfPizzaRepository) {
        handingOutOfPizzaRepository.sendEvent(this);
    }

    public void save(HandingOutOfPizzaRepository handingOutOfPizzaRepository) {
        handingOutOfPizzaRepository.save(this);
    }

    public static PizzaHandedOut of(OrderReference orderReference) {
        return new PizzaHandedOut(orderReference);
    }
}