package dev.jonathanstronkhorst.modularpizzahut.deliverydriver.infrastructure;

import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.DeliveringOfPizzaRepository;
import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.aggregate.DeliveringOfPizza;
import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.aggregate.order.OrderReference;
import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.event.PizzaDelivered;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeliveringOfPizzaRepositoryAdapter implements DeliveringOfPizzaRepository {
    @Override
    public Optional<DeliveringOfPizza> findDeliveringOfPizza(OrderReference orderReference) {
        return Optional.empty();
    }

    @Override
    public void sendEvent(PizzaDelivered pizzaDelivered) {

    }

    @Override
    public void save(PizzaDelivered pizzaDelivered) {

    }
}
