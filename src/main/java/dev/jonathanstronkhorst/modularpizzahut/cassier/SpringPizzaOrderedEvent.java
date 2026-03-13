package dev.jonathanstronkhorst.modularpizzahut.cassier;

import dev.jonathanstronkhorst.modularpizzahut.cassier.infrastructure.SpringOrderOfPizzasEventPublisher;
import java.util.UUID;
import org.springframework.context.ApplicationEvent;

public class SpringPizzaOrderedEvent extends ApplicationEvent {
    private final UUID orderReference;

    public SpringPizzaOrderedEvent(SpringOrderOfPizzasEventPublisher publisher, UUID orderReference) {
        super(publisher);
        this.orderReference = orderReference;
    }

    public UUID getOrderReference() {
        return orderReference;
    }

    public static SpringPizzaOrderedEvent of(SpringOrderOfPizzasEventPublisher publisher, UUID orderReference) {
        return new SpringPizzaOrderedEvent(publisher, orderReference);
    }
}
