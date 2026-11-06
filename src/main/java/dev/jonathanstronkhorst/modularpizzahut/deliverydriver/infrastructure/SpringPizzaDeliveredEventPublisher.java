package dev.jonathanstronkhorst.modularpizzahut.deliverydriver.infrastructure;

import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.SpringPizzaDeliveredEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class SpringPizzaDeliveredEventPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    public SpringPizzaDeliveredEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publish(SpringPizzaDeliveredEvent event) {
        applicationEventPublisher.publishEvent(event);
    }
}