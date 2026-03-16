package dev.jonathanstronkhorst.modularpizzahut.cassier.infrastructure;

import dev.jonathanstronkhorst.modularpizzahut.cassier.SpringPizzaOrderedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class SpringOrderOfPizzasEventPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    public SpringOrderOfPizzasEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publish(SpringPizzaOrderedEvent event) {
        applicationEventPublisher.publishEvent(event);
    }
}
