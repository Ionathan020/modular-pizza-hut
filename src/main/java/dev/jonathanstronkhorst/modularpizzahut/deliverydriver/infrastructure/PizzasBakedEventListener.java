package dev.jonathanstronkhorst.modularpizzahut.deliverydriver.infrastructure;

import dev.jonathanstronkhorst.modularpizzahut.cook.SpringPizzasBakedEvent;
import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.DeliveringOfPizzaRepository;
import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.DeliveringOfPizzaService;
import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.aggregate.order.CustomerDetails;
import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.aggregate.order.DeliveryAddress;
import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.aggregate.order.OrderReference;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component("deliveryPizzasBakedEventListener")
public class PizzasBakedEventListener implements ApplicationListener<SpringPizzasBakedEvent> {
    private final DeliveringOfPizzaService deliveringOfPizzaService;
    private final DeliveringOfPizzaRepository deliveringOfPizzaRepository;

    public PizzasBakedEventListener(DeliveringOfPizzaService deliveringOfPizzaService,
                                    DeliveringOfPizzaRepository deliveringOfPizzaRepository) {
        this.deliveringOfPizzaService = deliveringOfPizzaService;
        this.deliveringOfPizzaRepository = deliveringOfPizzaRepository;
    }

    @Override
    public void onApplicationEvent(SpringPizzasBakedEvent event) {
        if (!event.isDeliveryOrder()) {
            return;
        }
        OrderReference orderReference = OrderReference.of(event.getOrderReference());
        deliveringOfPizzaRepository.saveInitialOrder(
                orderReference,
                CustomerDetails.of(event.getName(), event.getPhoneNumber()),
                DeliveryAddress.of(event.getAddress()));
        deliveringOfPizzaService.deliverPizza(orderReference);
    }
}