package dev.jonathanstronkhorst.modularpizzahut.handout.domain.handout_of_pizza;

import dev.jonathanstronkhorst.modularpizzahut.handout.domain.handout_of_pizza.aggregate.HandoutOfPizza;
import dev.jonathanstronkhorst.modularpizzahut.handout.domain.handout_of_pizza.aggregate.order.OrderReference;
import dev.jonathanstronkhorst.modularpizzahut.handout.domain.handout_of_pizza.aggregate.pizza.HandoutOfPizzaResult;
import dev.jonathanstronkhorst.modularpizzahut.handout.domain.handout_of_pizza.command.HandoutPizza;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class HandoutOfPizzaService {
    private static final Logger logger = LoggerFactory.getLogger(HandoutOfPizzaService.class);
    private final HandoutOfPizzaRepository handoutOfPizzaRepository;

    public HandoutOfPizzaService(HandoutOfPizzaRepository handoutOfPizzaRepository) {
        this.handoutOfPizzaRepository = handoutOfPizzaRepository;
    }

    public HandoutOfPizzaResult handoutPizza(OrderReference orderReference) {
        logger.info("Handing out pizza for order: {}", orderReference.orderReference());
        HandoutOfPizza handoutOfPizza = getHandoutOfPizza(orderReference);
        if (handoutOfPizza == null) {
            return HandoutOfPizzaResult.of(null, null);
        }
        HandoutOfPizzaResult result = handoutOfPizza.handleCommand(HandoutPizza.of(orderReference));
        if (result.getEvent() != null) {
            result.getEvent().publish(handoutOfPizzaRepository);
            result.getEvent().save(handoutOfPizzaRepository);
        }
        return result;
    }

    private HandoutOfPizza getHandoutOfPizza(OrderReference orderReference) {
        return handoutOfPizzaRepository.findHandoutOfPizza(orderReference).orElse(null);
    }
}
