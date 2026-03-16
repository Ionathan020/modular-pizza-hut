package dev.jonathanstronkhorst.modularpizzahut.cashier.infrastructure;

import dev.jonathanstronkhorst.modularpizzahut.cashier.SpringPizzaOrderedEvent;
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
