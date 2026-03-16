package dev.jonathanstronkhorst.modularpizzahut.cook;

import dev.jonathanstronkhorst.modularpizzahut.cook.infrastructure.SpringPizzaBakedEventPublisher;
import java.util.UUID;
import org.springframework.context.ApplicationEvent;
import org.springframework.scheduling.annotation.Async;

@Async
public class SpringPizzaBakedEvent extends ApplicationEvent {
    private final UUID orderReference;
    private final boolean isDeliveryOrder;

    public SpringPizzaBakedEvent(SpringPizzaBakedEventPublisher publisher, UUID orderReference, boolean isDeliveryOrder) {
        super(publisher);
        this.orderReference = orderReference;
        this.isDeliveryOrder = isDeliveryOrder;
    }

    public UUID getOrderReference() {
        return orderReference;
    }

    public boolean isDeliveryOrder() {
        return isDeliveryOrder;
    }

    public static SpringPizzaBakedEvent of(SpringPizzaBakedEventPublisher publisher, UUID orderReference, boolean isDeliveryOrder) {
        return new SpringPizzaBakedEvent(publisher, orderReference, isDeliveryOrder);
    }
}
