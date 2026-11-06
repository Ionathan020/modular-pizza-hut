package dev.jonathanstronkhorst.modularpizzahut.cook.infrastructure;

import dev.jonathanstronkhorst.modularpizzahut.cook.SpringPizzasBakedEvent;
import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.BakingOfPizzaRepository;
import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.aggregate.BakingOfPizzas;
import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.aggregate.order.IsDeliveryOrder;
import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.aggregate.order.OrderReference;
import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.aggregate.pizza.Pizza;
import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.event.PizzasBaked;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class BakingOfPizzaRepositoryAdapter implements BakingOfPizzaRepository {
    private final BakingOfPizzaJPARepository bakingOfPizzaJPARepository;
    private final SpringPizzasBakedEventPublisher springPizzasBakedEventPublisher;

    public BakingOfPizzaRepositoryAdapter(BakingOfPizzaJPARepository bakingOfPizzaJPARepository,
                                          SpringPizzasBakedEventPublisher springPizzasBakedEventPublisher) {
        this.bakingOfPizzaJPARepository = bakingOfPizzaJPARepository;
        this.springPizzasBakedEventPublisher = springPizzasBakedEventPublisher;
    }

    @Override
    public Optional<BakingOfPizzas> findBakingOfPizzas(OrderReference orderReference) {
        return bakingOfPizzaJPARepository.findByOrderReference(orderReference.orderReference())
                .map(document -> BakingOfPizzas.of(
                        OrderReference.of(document.getOrderReference()),
                        IsDeliveryOrder.of(document.isDeliveryOrder()),
                        Pizza.getPizzasForIds(document.getPizzaIds())));
    }

    @Override
    public void sendEvent(PizzasBaked pizzasBaked) {
        BakingOfPizzaDocument document = getDocument(pizzasBaked.getOrderReference());
        springPizzasBakedEventPublisher.publish(SpringPizzasBakedEvent.of(
                springPizzasBakedEventPublisher,
                document.getOrderReference(),
                document.getName(),
                document.getPhoneNumber(),
                document.isDeliveryOrder(),
                document.getAddress(),
                document.getPizzaIds()));
    }

    @Override
    public void save(PizzasBaked pizzasBaked) {
        BakingOfPizzaDocument document = getDocument(pizzasBaked.getOrderReference());
        document.setOrderStatus(pizzasBaked.getOrderStatus().name());
        bakingOfPizzaJPARepository.save(document);
    }

    @Override
    public void saveInitialOrder(OrderReference orderReference,
                                 String name,
                                 String phoneNumber,
                                 boolean isDeliveryOrder,
                                 String address,
                                 List<Integer> pizzaIds) {
        bakingOfPizzaJPARepository.save(BakingOfPizzaDocument.of(
                orderReference.orderReference(), name, phoneNumber, isDeliveryOrder, address, pizzaIds, "NEW"));
    }

    private BakingOfPizzaDocument getDocument(OrderReference orderReference) {
        return bakingOfPizzaJPARepository.findByOrderReference(orderReference.orderReference())
                .orElseThrow(() -> new IllegalStateException("No baking order found for " + orderReference.orderReference()));
    }
}
