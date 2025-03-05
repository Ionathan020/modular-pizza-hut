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
        System.out.println("Start baking fo0r order: " + orderReference.orderReference().toString());
        BakingOfPizzas bakingOfPizzas = getBakingOfPizzasByOrderReference(orderReference);
        if (bakingOfPizzas == null) {
            System.out.println("Couldn't find order: " + orderReference.orderReference().toString());
            return BakedPizzaResult.of(null,null,null);
        }
        BakedPizzaResult bakedPizzaResult = bakingOfPizzas.handleCommand(
                BakePizzas.of(orderReference, OrderStatus.BAKED));
        if (bakedPizzaResult.getEvent() != null) {
            System.out.println("Even wachten!");
            bakedPizzaResult.getEvent().publish(bakingOfPizzaRepository);
            bakedPizzaResult.getEvent().save(bakingOfPizzaRepository);
        }
        System.out.println("Done baking for order: " + orderReference.orderReference().toString());
        return bakedPizzaResult;
    }

    private BakingOfPizzas getBakingOfPizzasByOrderReference(OrderReference orderReference) {
        return bakingOfPizzaRepository.findBakingOfPizzas(orderReference).orElse(null);
    }
}
