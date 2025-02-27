package dev.jonathanstronkhorst.modularpizzahut.cook;

import dev.jonathanstronkhorst.modularpizzahut.cook.infrastructure.SpringPizzaBakedEventPublisher;
import java.util.UUID;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import org.springframework.scheduling.annotation.Async;

@Getter
@Async
public class SpringPizzaBakedEvent extends ApplicationEvent {
    private final UUID orderReference;

    public SpringPizzaBakedEvent(SpringPizzaBakedEventPublisher publisher, UUID orderReference) {
        super(publisher);
        this.orderReference = orderReference;
    }

    public static SpringPizzaBakedEvent of(SpringPizzaBakedEventPublisher publisher, UUID orderReference) {
        return new SpringPizzaBakedEvent(publisher, orderReference);
    }
}
