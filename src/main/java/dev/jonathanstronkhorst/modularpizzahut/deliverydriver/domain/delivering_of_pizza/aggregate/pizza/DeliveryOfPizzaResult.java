package dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.aggregate.pizza;

import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.BaseDeliveringOfPizzaEvent;
import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.aggregate.order.OrderReference;
import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.aggregate.order.OrderStatus;

public class DeliveryOfPizzaResult {
    private final OrderReference orderReference;
    private final BaseDeliveringOfPizzaEvent event;
    private final OrderStatus orderStatus;

    public DeliveryOfPizzaResult(OrderReference orderReference, BaseDeliveringOfPizzaEvent event, OrderStatus orderStatus) {
        this.orderReference = orderReference;
        this.event = event;
        this.orderStatus = orderStatus;
    }

    public OrderReference getOrderReference() {
        return orderReference;
    }

    public BaseDeliveringOfPizzaEvent getEvent() {
        return event;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public static DeliveryOfPizzaResult of(OrderReference orderReference, BaseDeliveringOfPizzaEvent event, OrderStatus orderStatus) {
        return new DeliveryOfPizzaResult(orderReference, event, orderStatus);
    }

    public static DeliveryOfPizzaResult of(OrderReference orderReference, OrderStatus orderStatus) {
        return new DeliveryOfPizzaResult(orderReference, null, orderStatus);
    }
}
