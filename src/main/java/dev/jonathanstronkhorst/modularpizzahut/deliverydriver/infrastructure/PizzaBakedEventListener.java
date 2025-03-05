package dev.jonathanstronkhorst.modularpizzahut.deliverydriver.infrastructure;

import dev.jonathanstronkhorst.modularpizzahut.cook.SpringPizzaBakedEvent;
import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.DeliveringOfPizzaService;
import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.aggregate.order.OrderReference;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PizzaBakedEventListener  implements ApplicationListener<SpringPizzaBakedEvent> {
    private final DeliveringOfPizzaService deliveringOfPizzaService;
    @Override
    public void onApplicationEvent(SpringPizzaBakedEvent event) {
        deliveringOfPizzaService.deliverPizza(OrderReference.of(event.getOrderReference()));
    }
}
