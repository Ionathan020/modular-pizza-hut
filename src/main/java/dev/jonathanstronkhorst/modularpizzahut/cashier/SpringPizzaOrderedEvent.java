package dev.jonathanstronkhorst.modularpizzahut.cashier;

import dev.jonathanstronkhorst.modularpizzahut.cashier.infrastructure.SpringOrderOfPizzasEventPublisher;
import java.util.UUID;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class SpringPizzaOrderedEvent extends ApplicationEvent {
    private final UUID orderReference;



    public SpringPizzaOrderedEvent(SpringOrderOfPizzasEventPublisher publisher, UUID orderReference) {
        super(publisher);
        this.orderReference = orderReference;
    }

    public static SpringPizzaOrderedEvent of(SpringOrderOfPizzasEventPublisher publisher, UUID orderReference) {
        return new SpringPizzaOrderedEvent(publisher, orderReference);
    }
}
