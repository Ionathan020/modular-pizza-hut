package dev.jonathanstronkhorst.modularpizzahut.cashier.domain.order_of_pizzas.event;

import dev.jonathanstronkhorst.modularpizzahut.cashier.domain.BasePizzaOrderEvent;
import dev.jonathanstronkhorst.modularpizzahut.cashier.domain.order_of_pizzas.OrderOfPizzaRepository;
import dev.jonathanstronkhorst.modularpizzahut.cashier.domain.order_of_pizzas.aggregate.order.*;
import dev.jonathanstronkhorst.modularpizzahut.cashier.domain.order_of_pizzas.aggregate.pizza.Pizza;
import java.util.List;

public class PizzaOrdered implements BasePizzaOrderEvent {
    private final OrderReference orderReference;
    private final OrderStatus orderStatus;
    private final CustomerDetails customerDetails;
    private final IsDeliveryOrder isDeliveryOrder;
    private final DeliveryAddress deliveryAddress;
    private final List<Pizza> pizzas;

    private PizzaOrdered(OrderReference orderReference,
                         OrderStatus orderStatus,
                         CustomerDetails customerDetails,
                         IsDeliveryOrder isDeliveryOrder,
                         DeliveryAddress deliveryAddress,
                         List<Pizza> pizzas) {
        this.orderReference = orderReference;
        this.orderStatus = orderStatus;
        this.customerDetails = customerDetails;
        this.isDeliveryOrder = isDeliveryOrder;
        this.deliveryAddress = deliveryAddress;
        this.pizzas = pizzas;
    }

    public static PizzaOrdered of(OrderReference orderReference,
                                  OrderStatus orderStatus,
                                  CustomerDetails customerDetails,
                                  IsDeliveryOrder isDeliveryOrder,
                                  DeliveryAddress deliveryAddress,
                                  List<Pizza> pizzas) {
        return new PizzaOrdered(orderReference, orderStatus, customerDetails, isDeliveryOrder, deliveryAddress, pizzas);
    }

    public OrderReference getOrderReference() {
        return orderReference;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public CustomerDetails getCustomerDetails() {
        return customerDetails;
    }

    public IsDeliveryOrder getIsDeliveryOrder() {
        return isDeliveryOrder;
    }

    public DeliveryAddress getDeliveryAddress() {
        return deliveryAddress;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    @Override
    public void publish(OrderOfPizzaRepository orderOfPizzaRepository) {
        orderOfPizzaRepository.sendEvent(this);
    }

    @Override
    public void save(OrderOfPizzaRepository orderOfPizzaRepository) {
        orderOfPizzaRepository.save(this);
    }
}
