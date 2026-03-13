package dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza;


import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.aggregate.DeliveringOfPizza;
import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.aggregate.order.OrderReference;
import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.aggregate.pizza.DeliveryOfPizzaResult;
import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.command.DeliverPizza;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DeliveringOfPizzaService {
    private static final Logger logger = LoggerFactory.getLogger(DeliveringOfPizzaService.class);
    private final DeliveringOfPizzaRepository deliveringOfPizzaRepository;

    public DeliveringOfPizzaService(DeliveringOfPizzaRepository deliveringOfPizzaRepository) {
        this.deliveringOfPizzaRepository = deliveringOfPizzaRepository;
    }

    public DeliveryOfPizzaResult deliverPizza(OrderReference orderReference) {
        logger.info("Nog even wachten!");
        DeliveringOfPizza deliveringOfPizza = getDeliveringOfPizza(orderReference);
        if (deliveringOfPizza == null) {
            return DeliveryOfPizzaResult.of(null, null, null);
        }
        DeliveryOfPizzaResult deliveryOfPizzaResult = deliveringOfPizza.handleCommand(DeliverPizza.of(orderReference));
        if (deliveryOfPizzaResult.getEvent() != null) {
            deliveryOfPizzaResult.getEvent().publish(deliveringOfPizzaRepository);
            deliveryOfPizzaResult.getEvent().save(deliveringOfPizzaRepository);
        }
        return deliveryOfPizzaResult;
    }

    private DeliveringOfPizza getDeliveringOfPizza(OrderReference orderReference) {
        return deliveringOfPizzaRepository.findDeliveringOfPizza(orderReference).orElse(null);
    }
}
