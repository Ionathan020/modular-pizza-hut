package dev.jonathanstronkhorst.modularpizzahut.cook.infrastructure;

import dev.jonathanstronkhorst.modularpizzahut.cook.SpringPizzasBakedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class SpringPizzasBakedEventPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    public SpringPizzasBakedEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publish(SpringPizzasBakedEvent event) {
        applicationEventPublisher.publishEvent(event);
    }
}