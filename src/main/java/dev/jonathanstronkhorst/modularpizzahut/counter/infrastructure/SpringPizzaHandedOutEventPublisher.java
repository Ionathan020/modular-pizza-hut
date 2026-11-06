package dev.jonathanstronkhorst.modularpizzahut.counter.infrastructure;

import dev.jonathanstronkhorst.modularpizzahut.counter.SpringPizzaHandedOutEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class SpringPizzaHandedOutEventPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    public SpringPizzaHandedOutEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publish(SpringPizzaHandedOutEvent event) {
        applicationEventPublisher.publishEvent(event);
    }
}