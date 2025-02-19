package dev.jonathanstronkhorst.modularpizzahut.cassier.domain.order_of_pizzas.aggregate;

import dev.jonathanstronkhorst.modularpizzahut.cassier.domain.order_of_pizzas.aggregate.order.CustomerDetails;
import dev.jonathanstronkhorst.modularpizzahut.cassier.domain.order_of_pizzas.aggregate.order.DeliveryAddress;
import dev.jonathanstronkhorst.modularpizzahut.cassier.domain.order_of_pizzas.aggregate.order.IsDeliveryOrder;
import dev.jonathanstronkhorst.modularpizzahut.cassier.domain.order_of_pizzas.aggregate.pizza.Pizza;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
class OrderOfPizzasEntity {
    private final CustomerDetails customerDetails;
    private final IsDeliveryOrder isDeliveryOrder;
    private final DeliveryAddress deliveryAddress;
    private final List<Pizza> pizzas;

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
