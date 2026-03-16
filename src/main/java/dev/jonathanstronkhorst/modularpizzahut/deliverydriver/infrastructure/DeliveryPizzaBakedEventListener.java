package dev.jonathanstronkhorst.modularpizzahut.deliverydriver.infrastructure;

import dev.jonathanstronkhorst.modularpizzahut.cook.SpringPizzaBakedEvent;
import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.DeliveringOfPizzaService;
import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.aggregate.order.OrderReference;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class DeliveryPizzaBakedEventListener implements ApplicationListener<SpringPizzaBakedEvent> {
    private final DeliveringOfPizzaService deliveringOfPizzaService;

    public DeliveryPizzaBakedEventListener(DeliveringOfPizzaService deliveringOfPizzaService) {
        this.deliveringOfPizzaService = deliveringOfPizzaService;
    }

    @Override
    public void onApplicationEvent(SpringPizzaBakedEvent event) {
        if (event.isDeliveryOrder()) {
            deliveringOfPizzaService.deliverPizza(OrderReference.of(event.getOrderReference()));
        }
    }
}
