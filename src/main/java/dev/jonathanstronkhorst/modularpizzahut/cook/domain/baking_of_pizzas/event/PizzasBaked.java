package dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.event;

import dev.jonathanstronkhorst.modularpizzahut.cook.domain.BasePizzaBakedEvent;
import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.BakingOfPizzaRepository;
import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.aggregate.order.IsDeliveryOrder;
import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.aggregate.order.OrderReference;
import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.aggregate.order.OrderStatus;
import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.aggregate.pizza.Pizza;
import java.util.List;

public class PizzasBaked implements BasePizzaBakedEvent {
    private final OrderReference orderReference;
    private final OrderStatus orderStatus;
    private final IsDeliveryOrder isDeliveryOrder;
    private final List<Pizza> pizzas;

    private PizzasBaked(OrderReference orderReference, OrderStatus orderStatus, IsDeliveryOrder isDeliveryOrder, List<Pizza> pizzas) {
        this.orderReference = orderReference;
        this.orderStatus = orderStatus;
        this.isDeliveryOrder = isDeliveryOrder;
        this.pizzas = pizzas;
    }

    public static PizzasBaked of(OrderReference orderReference, OrderStatus orderStatus, IsDeliveryOrder isDeliveryOrder, List<Pizza> pizzas) {
        return new PizzasBaked(orderReference, orderStatus, isDeliveryOrder, pizzas);
    }

    public OrderReference getOrderReference() {
        return orderReference;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public IsDeliveryOrder getIsDeliveryOrder() {
        return isDeliveryOrder;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    @Override
    public void publish(BakingOfPizzaRepository bakingOfPizzaRepository) {
        bakingOfPizzaRepository.sendEvent(this);
    }

    @Override
    public void save(BakingOfPizzaRepository bakingOfPizzaRepository) {
        bakingOfPizzaRepository.save(this);
    }
}
