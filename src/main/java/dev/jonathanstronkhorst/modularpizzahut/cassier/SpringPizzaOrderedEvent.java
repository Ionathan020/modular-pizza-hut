package dev.jonathanstronkhorst.modularpizzahut.cassier;

import dev.jonathanstronkhorst.modularpizzahut.cassier.domain.order_of_pizzas.event.PizzasOrdered;
import dev.jonathanstronkhorst.modularpizzahut.cassier.infrastructure.SpringOrderOfPizzasEventPublisher;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class SpringPizzaOrderedEvent extends ApplicationEvent {
    private final PizzasOrdered pizzasOrdered;
    public SpringPizzaOrderedEvent(SpringOrderOfPizzasEventPublisher publisher, PizzasOrdered event) {
        super(publisher);
        this.pizzasOrdered = event;
    }

    public static SpringPizzaOrderedEvent of(SpringOrderOfPizzasEventPublisher publisher, PizzasOrdered event) {
        return new SpringPizzaOrderedEvent(publisher, event);
    }
}
