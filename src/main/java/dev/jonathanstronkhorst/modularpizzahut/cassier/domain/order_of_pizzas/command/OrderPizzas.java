package dev.jonathanstronkhorst.modularpizzahut.cassier.domain.order_of_pizzas.command;

import dev.jonathanstronkhorst.modularpizzahut.cassier.domain.order_of_pizzas.aggregate.order.CustomerDetails;
import dev.jonathanstronkhorst.modularpizzahut.cassier.domain.order_of_pizzas.aggregate.order.DeliveryAddress;
import dev.jonathanstronkhorst.modularpizzahut.cassier.domain.order_of_pizzas.aggregate.order.IsDeliveryOrder;
import dev.jonathanstronkhorst.modularpizzahut.cassier.domain.order_of_pizzas.aggregate.pizza.Pizza;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(staticName = "of")
public class OrderPizzas {
    private final CustomerDetails customerDetails;
    private final IsDeliveryOrder isDeliveryOrder;
    private final DeliveryAddress deliveryAddress;
    private final List<Pizza> pizzas;
}
