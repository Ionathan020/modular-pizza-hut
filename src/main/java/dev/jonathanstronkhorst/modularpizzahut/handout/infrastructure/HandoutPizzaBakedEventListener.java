package dev.jonathanstronkhorst.modularpizzahut.handout.infrastructure;

import dev.jonathanstronkhorst.modularpizzahut.cook.SpringPizzaBakedEvent;
import dev.jonathanstronkhorst.modularpizzahut.handout.domain.handout_of_pizza.HandoutOfPizzaService;
import dev.jonathanstronkhorst.modularpizzahut.handout.domain.handout_of_pizza.aggregate.order.OrderReference;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class HandoutPizzaBakedEventListener implements ApplicationListener<SpringPizzaBakedEvent> {
    private final HandoutOfPizzaService handoutOfPizzaService;

    public HandoutPizzaBakedEventListener(HandoutOfPizzaService handoutOfPizzaService) {
        this.handoutOfPizzaService = handoutOfPizzaService;
    }

    @Override
    public void onApplicationEvent(SpringPizzaBakedEvent event) {
        if (!event.isDeliveryOrder()) {
            handoutOfPizzaService.handoutPizza(OrderReference.of(event.getOrderReference()));
        }
    }
}
