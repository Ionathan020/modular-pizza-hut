package dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas;

import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.aggregate.BakingOfPizzas;
import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.aggregate.order.OrderReference;
import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.aggregate.order.OrderStatus;
import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.aggregate.pizza.BakedPizzaResult;
import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.command.BakePizzas;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BakingOfPizzaService {
    private final BakingOfPizzaRepository bakingOfPizzaRepository;
    private final BakingOfPizzaProvider bakingOfPizzaProvider;

    public BakedPizzaResult bakePizza(OrderReference orderReference) {
        BakingOfPizzas bakingOfPizzas = getBakingOfPizzasByOrderReference(orderReference);
        if (bakingOfPizzas == null) {
            return BakedPizzaResult.of(null,null,null);
        }
        BakedPizzaResult bakedPizzaResult = bakingOfPizzas.handleCommand(
                BakePizzas.of(orderReference, OrderStatus.BAKED));
        if (bakedPizzaResult.getEvent() != null) {
            bakedPizzaResult.getEvent().save(bakingOfPizzaRepository);
            bakedPizzaResult.getEvent().save(bakingOfPizzaRepository);
        }
        return bakedPizzaResult;
    }

    private BakingOfPizzas getBakingOfPizzasByOrderReference(OrderReference orderReference) {
        return bakingOfPizzaRepository.findBakingOfPizzas(orderReference).orElse(null);
    }
}
