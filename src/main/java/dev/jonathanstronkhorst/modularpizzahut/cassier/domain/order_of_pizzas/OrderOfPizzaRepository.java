package dev.jonathanstronkhorst.modularpizzahut.cassier.domain.order_of_pizzas;

import dev.jonathanstronkhorst.modularpizzahut.cassier.domain.order_of_pizzas.aggregate.OrderOfPizzas;
import dev.jonathanstronkhorst.modularpizzahut.cassier.domain.order_of_pizzas.aggregate.order.OrderNumber;
import dev.jonathanstronkhorst.modularpizzahut.cassier.domain.order_of_pizzas.event.PizzasOrdered;
import java.util.Optional;

public interface OrderOfPizzaRepository {
    Optional<OrderOfPizzas> findOrderOfPizzas(OrderNumber orderNumber);

    void sendEvent(PizzasOrdered pizzasOrdered);

    void save(OrderOfPizzas orderOfPizzas);
}
