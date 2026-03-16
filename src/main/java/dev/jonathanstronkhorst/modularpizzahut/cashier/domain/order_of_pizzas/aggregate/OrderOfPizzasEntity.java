package dev.jonathanstronkhorst.modularpizzahut.cashier.domain.order_of_pizzas.aggregate;

import dev.jonathanstronkhorst.modularpizzahut.cashier.domain.order_of_pizzas.aggregate.order.CustomerDetails;
import dev.jonathanstronkhorst.modularpizzahut.cashier.domain.order_of_pizzas.aggregate.order.DeliveryAddress;
import dev.jonathanstronkhorst.modularpizzahut.cashier.domain.order_of_pizzas.aggregate.order.IsDeliveryOrder;
import dev.jonathanstronkhorst.modularpizzahut.cashier.domain.order_of_pizzas.aggregate.pizza.Pizza;
import java.util.List;

class OrderOfPizzasEntity {
    private final CustomerDetails customerDetails;
    private final IsDeliveryOrder isDeliveryOrder;
    private final DeliveryAddress deliveryAddress;
    private final List<Pizza> pizzas;

    public OrderOfPizzasEntity(CustomerDetails customerDetails,
                                IsDeliveryOrder isDeliveryOrder,
                                DeliveryAddress deliveryAddress,
                                List<Pizza> pizzas) {
        this.customerDetails = customerDetails;
        this.isDeliveryOrder = isDeliveryOrder;
        this.deliveryAddress = deliveryAddress;
        this.pizzas = pizzas;
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

    public static OrderOfPizzasEntity of(CustomerDetails customerDetails,
                                         IsDeliveryOrder isDeliveryOrder,
                                         DeliveryAddress deliveryAddress, List<Pizza> pizzas) {
        return new OrderOfPizzasEntity(customerDetails, isDeliveryOrder, deliveryAddress, pizzas);
    }

    public boolean hasPizzaContainingPineapple() {
        for (Pizza pizza : pizzas) {
            if (pizza.containsPineapple()) {
                return true;
            }
        }
        return false;
    }
}
