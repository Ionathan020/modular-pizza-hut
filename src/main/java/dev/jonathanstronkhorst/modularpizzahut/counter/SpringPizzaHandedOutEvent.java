package dev.jonathanstronkhorst.modularpizzahut.counter;

import dev.jonathanstronkhorst.modularpizzahut.counter.infrastructure.SpringPizzaHandedOutEventPublisher;
import java.util.UUID;
import org.springframework.context.ApplicationEvent;

public class SpringPizzaHandedOutEvent extends ApplicationEvent {
    private final UUID orderReference;

    public SpringPizzaHandedOutEvent(SpringPizzaHandedOutEventPublisher publisher, UUID orderReference) {
        super(publisher);
        this.orderReference = orderReference;
    }

    public UUID getOrderReference() {
        return orderReference;
    }
}