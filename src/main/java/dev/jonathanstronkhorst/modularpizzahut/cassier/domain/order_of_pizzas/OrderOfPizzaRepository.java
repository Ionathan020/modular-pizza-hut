package dev.jonathanstronkhorst.modularpizzahut.cassier.domain.order_of_pizzas;

import dev.jonathanstronkhorst.modularpizzahut.cassier.domain.order_of_pizzas.aggregate.OrderOfPizzas;
import dev.jonathanstronkhorst.modularpizzahut.cassier.domain.order_of_pizzas.aggregate.order.CustomerDetails;
import dev.jonathanstronkhorst.modularpizzahut.cassier.domain.order_of_pizzas.aggregate.order.DeliveryAddress;
import dev.jonathanstronkhorst.modularpizzahut.cassier.domain.order_of_pizzas.aggregate.order.IsDeliveryOrder;
import dev.jonathanstronkhorst.modularpizzahut.cassier.domain.order_of_pizzas.aggregate.pizza.Pizza;
import dev.jonathanstronkhorst.modularpizzahut.cassier.domain.order_of_pizzas.event.PizzaOrdered;
import java.util.List;
import java.util.Optional;

public interface OrderOfPizzaRepository {
    Optional<OrderOfPizzas> createOrderOfPizzas(CustomerDetails customerDetails, IsDeliveryOrder isDeliveryOrder, DeliveryAddress deliveryAddress, List<Pizza> pizzas);

    void sendEvent(PizzaOrdered pizzaOrdered);

    void save(PizzaOrdered pizzaOrdered);
}
