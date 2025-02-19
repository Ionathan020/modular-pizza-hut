package dev.jonathanstronkhorst.modularpizzahut.cassier.infrastructure;

import dev.jonathanstronkhorst.modularpizzahut.cassier.SpringPizzaOrderedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SpringOrderOfPizzasEventPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    public void publish(SpringPizzaOrderedEvent event) {
        applicationEventPublisher.publishEvent(event);
    }
}
