package dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas;

import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.aggregate.BakingOfPizzas;
import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.aggregate.order.OrderReference;
import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.aggregate.order.OrderStatus;
import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.aggregate.pizza.BakedPizzaResult;
import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.command.BakePizzas;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class BakingOfPizzaService {
    private static final Logger logger = LoggerFactory.getLogger(BakingOfPizzaService.class);
    private final BakingOfPizzaRepository bakingOfPizzaRepository;

    public BakingOfPizzaService(BakingOfPizzaRepository bakingOfPizzaRepository) {
        this.bakingOfPizzaRepository = bakingOfPizzaRepository;
    }

    public BakedPizzaResult bakePizza(OrderReference orderReference) {
        logger.info("Start baking for order: {}", orderReference.orderReference().toString());
        BakingOfPizzas bakingOfPizzas = getBakingOfPizzasByOrderReference(orderReference);
        if (bakingOfPizzas == null) {
            logger.warn("Couldn't find order: {}", orderReference.orderReference().toString());
            return BakedPizzaResult.of(null,null,null);
        }
        BakedPizzaResult bakedPizzaResult = bakingOfPizzas.handleCommand(
                BakePizzas.of(orderReference, OrderStatus.BAKED));
        if (bakedPizzaResult.getEvent() != null) {
            logger.info("Even wachten!");
            bakedPizzaResult.getEvent().publish(bakingOfPizzaRepository);
            bakedPizzaResult.getEvent().save(bakingOfPizzaRepository);
        }
        logger.info("Done baking for order: {}", orderReference.orderReference().toString());
        return bakedPizzaResult;
    }

    private BakingOfPizzas getBakingOfPizzasByOrderReference(OrderReference orderReference) {
        return bakingOfPizzaRepository.findBakingOfPizzas(orderReference).orElse(null);
    }
}
