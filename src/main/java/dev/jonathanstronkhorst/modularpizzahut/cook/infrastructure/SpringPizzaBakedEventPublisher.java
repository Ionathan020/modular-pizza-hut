package dev.jonathanstronkhorst.modularpizzahut.cook.infrastructure;

import dev.jonathanstronkhorst.modularpizzahut.cook.SpringPizzaBakedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class SpringPizzaBakedEventPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    public SpringPizzaBakedEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publish(SpringPizzaBakedEvent event) {
        applicationEventPublisher.publishEvent(event);
    }
}
