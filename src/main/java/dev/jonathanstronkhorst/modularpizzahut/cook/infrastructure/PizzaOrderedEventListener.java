package dev.jonathanstronkhorst.modularpizzahut.cook.infrastructure;

import dev.jonathanstronkhorst.modularpizzahut.cashier.SpringPizzaOrderedEvent;
import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.BakingOfPizzaRepository;
import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.BakingOfPizzaService;
import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.aggregate.order.OrderReference;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class PizzaOrderedEventListener implements ApplicationListener<SpringPizzaOrderedEvent> {
    private final BakingOfPizzaService bakingOfPizzaService;
    private final BakingOfPizzaRepository bakingOfPizzaRepository;

    public PizzaOrderedEventListener(BakingOfPizzaService bakingOfPizzaService, BakingOfPizzaRepository bakingOfPizzaRepository) {
        this.bakingOfPizzaService = bakingOfPizzaService;
        this.bakingOfPizzaRepository = bakingOfPizzaRepository;
    }

    @Override
    public void onApplicationEvent(SpringPizzaOrderedEvent event) {
        bakingOfPizzaRepository.saveInitialOrder(
                OrderReference.of(event.getOrderReference()),
                event.getName(),
                event.getPhoneNumber(),
                event.isDeliveryOrder(),
                event.getAddress(),
                event.getPizzaIds()
        );
        bakingOfPizzaService.bakePizza(OrderReference.of(event.getOrderReference()));
    }
}
