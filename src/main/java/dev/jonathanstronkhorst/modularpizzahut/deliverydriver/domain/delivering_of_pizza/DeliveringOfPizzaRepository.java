package dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza;

import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.aggregate.DeliveringOfPizza;
import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.aggregate.order.CustomerDetails;
import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.aggregate.order.DeliveryAddress;
import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.aggregate.order.OrderReference;
import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.event.PizzaDelivered;
import java.util.Optional;

public interface DeliveringOfPizzaRepository {
    Optional<DeliveringOfPizza> findDeliveringOfPizza(OrderReference orderReference);

    void saveInitialOrder(OrderReference orderReference, CustomerDetails customerDetails, DeliveryAddress deliveryAddress);

    void sendEvent(PizzaDelivered pizzaDelivered);

    void save(PizzaDelivered pizzaDelivered);
}
