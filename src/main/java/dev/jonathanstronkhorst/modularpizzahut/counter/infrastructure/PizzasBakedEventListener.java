package dev.jonathanstronkhorst.modularpizzahut.counter.infrastructure;

import dev.jonathanstronkhorst.modularpizzahut.counter.domain.handing_out_of_pizzas.HandingOutOfPizzaRepository;
import dev.jonathanstronkhorst.modularpizzahut.counter.domain.handing_out_of_pizzas.HandingOutOfPizzaService;
import dev.jonathanstronkhorst.modularpizzahut.counter.domain.handing_out_of_pizzas.aggregate.order.OrderReference;
import dev.jonathanstronkhorst.modularpizzahut.cook.SpringPizzasBakedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component("pickupPizzasBakedEventListener")
public class PizzasBakedEventListener implements ApplicationListener<SpringPizzasBakedEvent> {
    private final HandingOutOfPizzaService handingOutOfPizzaService;
    private final HandingOutOfPizzaRepository handingOutOfPizzaRepository;

    public PizzasBakedEventListener(HandingOutOfPizzaService handingOutOfPizzaService,
                                    HandingOutOfPizzaRepository handingOutOfPizzaRepository) {
        this.handingOutOfPizzaService = handingOutOfPizzaService;
        this.handingOutOfPizzaRepository = handingOutOfPizzaRepository;
    }

    @Override
    public void onApplicationEvent(SpringPizzasBakedEvent event) {
        if (event.isDeliveryOrder()) {
            return;
        }
        OrderReference orderReference = OrderReference.of(event.getOrderReference());
        handingOutOfPizzaRepository.saveInitialOrder(orderReference);
        handingOutOfPizzaService.handOutPizza(orderReference);
    }
}