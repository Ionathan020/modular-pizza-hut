package dev.jonathanstronkhorst.modularpizzahut.cashier;

import dev.jonathanstronkhorst.modularpizzahut.cashier.infrastructure.SpringOrderOfPizzasEventPublisher;
import java.util.List;
import java.util.UUID;
import org.springframework.context.ApplicationEvent;

public class SpringPizzaOrderedEvent extends ApplicationEvent {
    private final UUID orderReference;
    private final String name;
    private final String phoneNumber;
    private final boolean isDeliveryOrder;
    private final String address;
    private final List<Integer> pizzaIds;

    public SpringPizzaOrderedEvent(SpringOrderOfPizzasEventPublisher publisher,
                                   UUID orderReference,
                                   String name,
                                   String phoneNumber,
                                   boolean isDeliveryOrder,
                                   String address,
                                   List<Integer> pizzaIds) {
        super(publisher);
        this.orderReference = orderReference;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.isDeliveryOrder = isDeliveryOrder;
        this.address = address;
        this.pizzaIds = pizzaIds;
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

    public boolean isDeliveryOrder() {
        return isDeliveryOrder;
    }

    public String getAddress() {
        return address;
    }

    public List<Integer> getPizzaIds() {
        return pizzaIds;
    }

    public static SpringPizzaOrderedEvent of(SpringOrderOfPizzasEventPublisher publisher,
                                             UUID orderReference,
                                             String name,
                                             String phoneNumber,
                                             boolean isDeliveryOrder,
                                             String address,
                                             List<Integer> pizzaIds) {
        return new SpringPizzaOrderedEvent(publisher, orderReference, name, phoneNumber, isDeliveryOrder, address, pizzaIds);
    }
}
