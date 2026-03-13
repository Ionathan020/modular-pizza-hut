package dev.jonathanstronkhorst.modularpizzahut.cassier.domain.order_of_pizzas.aggregate.order;

import dev.jonathanstronkhorst.modularpizzahut.cassier.domain.BasePizzaOrderEvent;

public class PizzaOrderResult {
    private final OrderReference orderReference;
    private final BasePizzaOrderEvent event;
    private final OrderStatus orderStatus;

    public PizzaOrderResult(OrderReference orderReference, BasePizzaOrderEvent event, OrderStatus orderStatus) {
        this.orderReference = orderReference;
        this.event = event;
        this.orderStatus = orderStatus;
    }

    public OrderReference getOrderReference() {
        return orderReference;
    }

    public BasePizzaOrderEvent getEvent() {
        return event;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public static PizzaOrderResult of(OrderReference orderReference, BasePizzaOrderEvent event, OrderStatus orderStatus) {
        return new PizzaOrderResult(orderReference, event, orderStatus);
    }

    public static PizzaOrderResult of(OrderReference orderReference, OrderStatus orderStatus) {
        return new PizzaOrderResult(orderReference, null, orderStatus);
    }
}
