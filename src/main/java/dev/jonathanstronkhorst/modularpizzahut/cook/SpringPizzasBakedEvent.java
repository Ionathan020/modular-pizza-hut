package dev.jonathanstronkhorst.modularpizzahut.cook;

import dev.jonathanstronkhorst.modularpizzahut.cook.infrastructure.SpringPizzasBakedEventPublisher;
import java.util.List;
import java.util.UUID;
import org.springframework.context.ApplicationEvent;

public class SpringPizzasBakedEvent extends ApplicationEvent {
    private final UUID orderReference;
    private final String name;
    private final String phoneNumber;
    private final boolean isDeliveryOrder;
    private final String address;
    private final List<Integer> pizzaIds;

    public SpringPizzasBakedEvent(SpringPizzasBakedEventPublisher publisher,
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
        this.pizzaIds = List.copyOf(pizzaIds);
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

    public static SpringPizzasBakedEvent of(SpringPizzasBakedEventPublisher publisher,
                                            UUID orderReference,
                                            String name,
                                            String phoneNumber,
                                            boolean isDeliveryOrder,
                                            String address,
                                            List<Integer> pizzaIds) {
        return new SpringPizzasBakedEvent(publisher, orderReference, name, phoneNumber, isDeliveryOrder, address, pizzaIds);
    }
}