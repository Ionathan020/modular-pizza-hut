package dev.jonathanstronkhorst.modularpizzahut.deliverydriver;

import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.infrastructure.SpringPizzaDeliveredEventPublisher;
import java.util.UUID;
import org.springframework.context.ApplicationEvent;

public class SpringPizzaDeliveredEvent extends ApplicationEvent {
    private final UUID orderReference;
    private final String name;
    private final String phoneNumber;
    private final String address;

    public SpringPizzaDeliveredEvent(SpringPizzaDeliveredEventPublisher publisher,
                                     UUID orderReference,
                                     String name,
                                     String phoneNumber,
                                     String address) {
        super(publisher);
        this.orderReference = orderReference;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public UUID getOrderReference() {
        return orderReference;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }
}