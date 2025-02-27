package dev.jonathanstronkhorst.modularpizzahut.cook.infrastructure;

import dev.jonathanstronkhorst.modularpizzahut.cassier.SpringPizzaOrderedEvent;
import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.BakingOfPizzaService;
import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.aggregate.order.OrderReference;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PizzaOrderedEventListener implements ApplicationListener<SpringPizzaOrderedEvent> {
    private final BakingOfPizzaService bakingOfPizzaService;


    @Override
    public void onApplicationEvent(SpringPizzaOrderedEvent event) {

        bakingOfPizzaService.bakePizza(OrderReference.of(event.getOrderReference()));
    }
}
