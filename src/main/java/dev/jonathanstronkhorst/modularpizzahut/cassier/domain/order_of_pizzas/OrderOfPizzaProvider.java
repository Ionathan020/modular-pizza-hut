package dev.jonathanstronkhorst.modularpizzahut.cassier.domain.order_of_pizzas;

import dev.jonathanstronkhorst.modularpizzahut.cassier.domain.order_of_pizzas.aggregate.OrderOfPizzas;
import dev.jonathanstronkhorst.modularpizzahut.cassier.domain.order_of_pizzas.aggregate.order.OrderNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OrderOfPizzaProvider {
    private final OrderOfPizzaRepository orderOfPizzaRepository;

    public OrderOfPizzas getOrderOfPizzas(OrderNumber orderNumber) {
        return orderOfPizzaRepository.findOrderOfPizzas(orderNumber).orElseThrow();
    }
}
