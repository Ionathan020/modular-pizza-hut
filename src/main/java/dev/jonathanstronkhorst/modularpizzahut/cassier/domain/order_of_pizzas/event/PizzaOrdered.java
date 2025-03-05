package dev.jonathanstronkhorst.modularpizzahut.cassier.domain.order_of_pizzas.event;

import dev.jonathanstronkhorst.modularpizzahut.cassier.domain.BasePizzaOrderEvent;
import dev.jonathanstronkhorst.modularpizzahut.cassier.domain.order_of_pizzas.OrderOfPizzaRepository;
import dev.jonathanstronkhorst.modularpizzahut.cassier.domain.order_of_pizzas.aggregate.order.*;
import dev.jonathanstronkhorst.modularpizzahut.cassier.domain.order_of_pizzas.aggregate.pizza.Pizza;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(staticName = "of")
public class PizzaOrdered implements BasePizzaOrderEvent {
    private final OrderReference orderReference;
    private final OrderStatus orderStatus;
    private final CustomerDetails customerDetails;
    private final IsDeliveryOrder isDeliveryOrder;
    private final DeliveryAddress deliveryAddress;
    private final List<Pizza> pizzas;


    @Override
    public void publish(OrderOfPizzaRepository orderOfPizzaRepository) {
        orderOfPizzaRepository.sendEvent(this);
    }

    @Override
    public void save(OrderOfPizzaRepository orderOfPizzaRepository) {
        orderOfPizzaRepository.save(this);
    }
}
