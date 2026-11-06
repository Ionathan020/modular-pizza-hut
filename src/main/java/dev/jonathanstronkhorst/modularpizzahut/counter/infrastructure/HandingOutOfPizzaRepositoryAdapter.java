package dev.jonathanstronkhorst.modularpizzahut.counter.infrastructure;

import dev.jonathanstronkhorst.modularpizzahut.counter.SpringPizzaHandedOutEvent;
import dev.jonathanstronkhorst.modularpizzahut.counter.domain.handing_out_of_pizzas.HandingOutOfPizzaRepository;
import dev.jonathanstronkhorst.modularpizzahut.counter.domain.handing_out_of_pizzas.aggregate.HandingOutOfPizza;
import dev.jonathanstronkhorst.modularpizzahut.counter.domain.handing_out_of_pizzas.aggregate.order.OrderReference;
import dev.jonathanstronkhorst.modularpizzahut.counter.domain.handing_out_of_pizzas.event.PizzaHandedOut;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.springframework.stereotype.Component;

@Component
public class HandingOutOfPizzaRepositoryAdapter implements HandingOutOfPizzaRepository {
    private final ConcurrentMap<UUID, Boolean> orders = new ConcurrentHashMap<>();
    private final SpringPizzaHandedOutEventPublisher springPizzaHandedOutEventPublisher;

    public HandingOutOfPizzaRepositoryAdapter(SpringPizzaHandedOutEventPublisher springPizzaHandedOutEventPublisher) {
        this.springPizzaHandedOutEventPublisher = springPizzaHandedOutEventPublisher;
    }

    @Override
    public Optional<HandingOutOfPizza> findHandingOutOfPizza(OrderReference orderReference) {
        return Optional.ofNullable(orders.get(orderReference.orderReference()))
                .map(ignored -> HandingOutOfPizza.of(orderReference));
    }

    @Override
    public void saveInitialOrder(OrderReference orderReference) {
        orders.put(orderReference.orderReference(), false);
    }

    @Override
    public void save(PizzaHandedOut pizzaHandedOut) {
        orders.put(pizzaHandedOut.getOrderReference().orderReference(), true);
    }

    @Override
    public void sendEvent(PizzaHandedOut pizzaHandedOut) {
        springPizzaHandedOutEventPublisher.publish(new SpringPizzaHandedOutEvent(
                springPizzaHandedOutEventPublisher,
                pizzaHandedOut.getOrderReference().orderReference()));
    }
}