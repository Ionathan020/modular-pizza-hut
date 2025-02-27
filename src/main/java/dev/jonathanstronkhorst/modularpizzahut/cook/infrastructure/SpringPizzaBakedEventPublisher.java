package dev.jonathanstronkhorst.modularpizzahut.cook.infrastructure;

import dev.jonathanstronkhorst.modularpizzahut.cook.SpringPizzaBakedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SpringPizzaBakedEventPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    public void publish(SpringPizzaBakedEvent event) {
        applicationEventPublisher.publishEvent(event);
    }
}
