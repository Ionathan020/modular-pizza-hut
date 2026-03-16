package dev.jonathanstronkhorst.modularpizzahut.cook.infrastructure;

import dev.jonathanstronkhorst.modularpizzahut.cook.SpringPizzaBakedEvent;
import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.BakingOfPizzaRepository;
import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.aggregate.BakingOfPizzas;
import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.aggregate.order.IsDeliveryOrder;
import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.aggregate.order.OrderReference;
import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.aggregate.pizza.Pizza;
import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.event.PizzasBaked;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class BakingOfPizzaRepositoryAdapter implements BakingOfPizzaRepository {
    private final BakingOfPizzaJPARepository bakingOfPizzaJPARepository;
    private final SpringPizzaBakedEventPublisher springPizzaBakedEventPublisher;

    public BakingOfPizzaRepositoryAdapter(BakingOfPizzaJPARepository bakingOfPizzaJPARepository, SpringPizzaBakedEventPublisher springPizzaBakedEventPublisher) {
        this.bakingOfPizzaJPARepository = bakingOfPizzaJPARepository;
        this.springPizzaBakedEventPublisher = springPizzaBakedEventPublisher;
    }

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
                SpringPizzaBakedEvent.of(springPizzaBakedEventPublisher, pizzasBaked.getOrderReference().orderReference(), pizzasBaked.getIsDeliveryOrder().isDeliveryOrder())
        );
    }

    @Override
    public void save(PizzasBaked pizzasBaked) {
        BakingOfPizzasDocument document = bakingOfPizzaJPARepository.findByOrderReference(pizzasBaked.getOrderReference().orderReference())
                .orElse(BakingOfPizzasDocument.of(
                        pizzasBaked.getOrderReference().orderReference(),
                        null, // name
                        null, // phoneNumber
                        pizzasBaked.getIsDeliveryOrder().isDeliveryOrder(),
                        null, // address
                        pizzasBaked.getPizzas().stream().map(Pizza::getId).collect(Collectors.toList())
                ));
        // Ensure pizzaIds and isDeliveryOrder are updated if they changed (though they shouldn't)
        document.setDeliveryOrder(pizzasBaked.getIsDeliveryOrder().isDeliveryOrder());
        document.setPizzaIds(pizzasBaked.getPizzas().stream().map(Pizza::getId).collect(Collectors.toList()));
        bakingOfPizzaJPARepository.save(document);
    }

    @Override
    public void saveInitialOrder(OrderReference orderReference, String name, String phoneNumber, boolean isDeliveryOrder, String address, java.util.List<Integer> pizzaIds) {
        BakingOfPizzasDocument document = bakingOfPizzaJPARepository.findByOrderReference(orderReference.orderReference())
                .orElse(BakingOfPizzasDocument.of(orderReference.orderReference(), name, phoneNumber, isDeliveryOrder, address, pizzaIds));
        
        // Update fields if they already exist (idempotency)
        document.setName(name);
        document.setPhoneNumber(phoneNumber);
        document.setDeliveryOrder(isDeliveryOrder);
        document.setAddress(address);
        document.setPizzaIds(pizzaIds);
        
        bakingOfPizzaJPARepository.save(document);
    }
}
