package dev.jonathanstronkhorst.modularpizzahut.cassier.domain.order_of_pizzas;

import dev.jonathanstronkhorst.modularpizzahut.cassier.domain.order_of_pizzas.aggregate.OrderOfPizzas;
import dev.jonathanstronkhorst.modularpizzahut.cassier.domain.order_of_pizzas.aggregate.order.CustomerDetails;
import dev.jonathanstronkhorst.modularpizzahut.cassier.domain.order_of_pizzas.aggregate.order.DeliveryAddress;
import dev.jonathanstronkhorst.modularpizzahut.cassier.domain.order_of_pizzas.aggregate.order.IsDeliveryOrder;
import dev.jonathanstronkhorst.modularpizzahut.cassier.domain.order_of_pizzas.aggregate.pizza.Pizza;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OrderOfPizzaProvider {
    private final OrderOfPizzaRepository orderOfPizzaRepository;

    public OrderOfPizzas newOrderOfPizzas(CustomerDetails customerDetails, IsDeliveryOrder isDeliveryOrder, DeliveryAddress deliveryAddress, List<Pizza> pizzas) {
        return orderOfPizzaRepository.createOrderOfPizzas(customerDetails, isDeliveryOrder, deliveryAddress, pizzas).orElse(OrderOfPizzas.of(customerDetails, isDeliveryOrder, deliveryAddress, pizzas));
    }
}
