package dev.jonathanstronkhorst.modularpizzahut.cassier.infrastructure;

import dev.jonathanstronkhorst.modularpizzahut.cassier.SpringPizzaOrderedEvent;
import dev.jonathanstronkhorst.modularpizzahut.cassier.domain.order_of_pizzas.OrderOfPizzaRepository;
import dev.jonathanstronkhorst.modularpizzahut.cassier.domain.order_of_pizzas.aggregate.OrderOfPizzas;
import dev.jonathanstronkhorst.modularpizzahut.cassier.domain.order_of_pizzas.aggregate.order.CustomerDetails;
import dev.jonathanstronkhorst.modularpizzahut.cassier.domain.order_of_pizzas.aggregate.order.DeliveryAddress;
import dev.jonathanstronkhorst.modularpizzahut.cassier.domain.order_of_pizzas.aggregate.order.IsDeliveryOrder;
import dev.jonathanstronkhorst.modularpizzahut.cassier.domain.order_of_pizzas.aggregate.pizza.Pizza;
import dev.jonathanstronkhorst.modularpizzahut.cassier.domain.order_of_pizzas.event.PizzaOrdered;
import java.util.*;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderOfPizzaRepositoryAdapter implements OrderOfPizzaRepository {

    private final OrderOfPizzaJPARepository orderOfPizzaJPARepository;
    private final SpringOrderOfPizzasEventPublisher springOrderOfPizzasEventPublisher;

    @Override
    public Optional<OrderOfPizzas> createOrderOfPizzas(CustomerDetails customerDetails, IsDeliveryOrder isDeliveryOrder, DeliveryAddress deliveryAddress, List<Pizza> pizzas) {
        return Optional.empty();
    }

    @Override
    public void sendEvent(PizzaOrdered pizzaOrdered) {
        springOrderOfPizzasEventPublisher.publish(
                SpringPizzaOrderedEvent.of(
                        springOrderOfPizzasEventPublisher,
                        pizzaOrdered.getOrderReference().orderReference()
                ));
        System.out.println("Event send for order: " + pizzaOrdered.getOrderReference().orderReference().toString());
    }

    @Override
    public void save(PizzaOrdered pizzaOrdered) {
        OrderOfPizzaDocument orderOfPizzaDocument = orderOfPizzaJPARepository.findByOrderReference(
                pizzaOrdered.getOrderReference().orderReference())
                .orElse(OrderOfPizzaDocument.of(
                        pizzaOrdered.getOrderReference().orderReference(),
                        pizzaOrdered.getCustomerDetails().name(),
                        pizzaOrdered.getCustomerDetails().phoneNumber(),
                        pizzaOrdered.getIsDeliveryOrder().isDeliveryOrder(),
                        pizzaOrdered.getDeliveryAddress().address(),
                        pizzaOrdered.getPizzas().stream()
                                .map(Pizza::getId).collect(Collectors.toList())));
        orderOfPizzaJPARepository.save(orderOfPizzaDocument);
        System.out.println("Information of order saved for order: " + pizzaOrdered.getOrderReference().orderReference().toString());
    }
}
