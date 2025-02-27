package dev.jonathanstronkhorst.modularpizzahut.cook.infrastructure;

import dev.jonathanstronkhorst.modularpizzahut.cook.SpringPizzaBakedEvent;
import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.BakingOfPizzaProvider;
import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.BakingOfPizzaRepository;
import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.aggregate.BakingOfPizzas;
import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.aggregate.order.IsDeliveryOrder;
import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.aggregate.order.OrderReference;
import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.aggregate.pizza.BakedPizzaResult;
import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.aggregate.pizza.Pizza;
import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.event.PizzasBaked;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BakingOfPizzaRepositoryAdapter implements BakingOfPizzaRepository {
    private final BakingOfPizzaProvider bakingOfPizzaProvider;
    BakingOfPizzaJPARepository bakingOfPizzaJPARepository;
    SpringPizzaBakedEventPublisher springPizzaBakedEventPublisher;

    @Override
    public Optional<BakingOfPizzas> findBakingOfPizzas(OrderReference orderReference) {
        Optional<BakingOfPizzasDocument> optionalBakingOfPizzasDocument = bakingOfPizzaJPARepository.findByOrderReference(orderReference.orderReference());
        if (optionalBakingOfPizzasDocument.isPresent()) {
            BakingOfPizzasDocument bakingOfPizzasDocument = optionalBakingOfPizzasDocument.get();
            return Optional.of(BakingOfPizzas.of(
                    OrderReference.of(bakingOfPizzasDocument.getOrderReference()),
                    IsDeliveryOrder.of(bakingOfPizzasDocument.isDeliveryOrder()),
                    Pizza.getPizzasForIds(bakingOfPizzasDocument.getPizzaIds())
            ));
        }
        return Optional.empty();
    }

    @Override
    public void sendEvent(PizzasBaked pizzasBaked) {
        springPizzaBakedEventPublisher.publish(
                SpringPizzaBakedEvent.of(springPizzaBakedEventPublisher, pizzasBaked.getOrderReference().orderReference())
        );
    }

    @Override
    public void save(PizzasBaked pizzasBaked) {
        BakingOfPizzasDocument bakingOfPizzasDocument = bakingOfPizzaJPARepository.findByOrderReference(pizzasBaked.getOrderReference().orderReference())
                .orElse(BakingOfPizzasDocument
                        .of(pizzasBaked.getOrderReference().orderReference(), pizzasBaked.getIsDeliveryOrder().isDeliveryOrder(), pizzasBaked.getPizzas().stream()
                                .map(Pizza::getId).collect(Collectors.toList())));
    }
}
