package dev.jonathanstronkhorst.modularpizzahut.cashier.infrastructure;

import dev.jonathanstronkhorst.modularpizzahut.cashier.SpringPizzaOrderedEvent;
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
