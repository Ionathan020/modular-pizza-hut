package dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.event;

import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.BaseDeliveringOfPizzaEvent;
import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.DeliveringOfPizzaRepository;
import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.aggregate.order.CustomerDetails;
import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.aggregate.order.DeliveryAddress;
import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.aggregate.order.OrderReference;
import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.aggregate.order.OrderStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(staticName = "of")
public class PizzaDelivered implements BaseDeliveringOfPizzaEvent {
    private final OrderReference orderReference;
    private final OrderStatus orderStatus;
    private final CustomerDetails customerDetails;
    private final DeliveryAddress deliveryAddress;

    @Override
    public void publish(DeliveringOfPizzaRepository bakingOfPizzaRepository) {
        bakingOfPizzaRepository.sendEvent(this);
    }

    @Override
    public void save(DeliveringOfPizzaRepository bakingOfPizzaRepository) {
        bakingOfPizzaRepository.save(this);
    }
}
