package dev.jonathanstronkhorst.modularpizzahut.cashier.infrastructure;

import dev.jonathanstronkhorst.modularpizzahut.cashier.SpringPizzaOrderedEvent;
import dev.jonathanstronkhorst.modularpizzahut.cashier.domain.order_of_pizzas.OrderOfPizzaRepository;
import dev.jonathanstronkhorst.modularpizzahut.cashier.domain.order_of_pizzas.aggregate.OrderOfPizzas;
import dev.jonathanstronkhorst.modularpizzahut.cashier.domain.order_of_pizzas.aggregate.order.CustomerDetails;
import dev.jonathanstronkhorst.modularpizzahut.cashier.domain.order_of_pizzas.aggregate.order.DeliveryAddress;
import dev.jonathanstronkhorst.modularpizzahut.cashier.domain.order_of_pizzas.aggregate.order.IsDeliveryOrder;
import dev.jonathanstronkhorst.modularpizzahut.cashier.domain.order_of_pizzas.aggregate.order.OrderReference;
import dev.jonathanstronkhorst.modularpizzahut.cashier.domain.order_of_pizzas.aggregate.pizza.Pizza;
import dev.jonathanstronkhorst.modularpizzahut.cashier.domain.order_of_pizzas.event.PizzaOrdered;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class OrderOfPizzaRepositoryAdapter implements OrderOfPizzaRepository {
    private static final Logger logger = LoggerFactory.getLogger(OrderOfPizzaRepositoryAdapter.class);

    private final OrderOfPizzaJPARepository orderOfPizzaJPARepository;
    private final SpringOrderOfPizzasEventPublisher springOrderOfPizzasEventPublisher;

    public OrderOfPizzaRepositoryAdapter(OrderOfPizzaJPARepository orderOfPizzaJPARepository, SpringOrderOfPizzasEventPublisher springOrderOfPizzasEventPublisher) {
        this.orderOfPizzaJPARepository = orderOfPizzaJPARepository;
        this.springOrderOfPizzasEventPublisher = springOrderOfPizzasEventPublisher;
    }

    @Override
    public Optional<OrderOfPizzas> createOrderOfPizzas(CustomerDetails customerDetails, IsDeliveryOrder isDeliveryOrder, DeliveryAddress deliveryAddress, List<Pizza> pizzas) {
        // Since we don't have a lookup by all these criteria and the domain currently always creates a NEW order with a new UUID,
        // we could potentially look for an existing order if that's the intention, 
        // but typically 'create' should just return empty if it doesn't exist or load it if we had an ID.
        // For now, let's keep it returning empty as it's a NEW order.
        // However, if the goal was to avoid creating duplicates if the same customer orders the same thing...
        return Optional.empty();
    }

    public Optional<OrderOfPizzas> findByOrderReference(OrderReference orderReference) {
        return orderOfPizzaJPARepository.findByOrderReference(orderReference.orderReference())
                .map(doc -> OrderOfPizzas.of(
                        CustomerDetails.of(doc.getName(), doc.getPhoneNumber()),
                        IsDeliveryOrder.of(doc.isDeliveryOrder()),
                        DeliveryAddress.of(doc.getAddress()),
                        Pizza.getPizzasForIds(doc.getPizzaIds())
                ));
    }

    @Override
    public void sendEvent(PizzaOrdered pizzaOrdered) {
        springOrderOfPizzasEventPublisher.publish(
                SpringPizzaOrderedEvent.of(
                        springOrderOfPizzasEventPublisher,
                        pizzaOrdered.getOrderReference().orderReference(),
                        pizzaOrdered.getCustomerDetails().name(),
                        pizzaOrdered.getCustomerDetails().phoneNumber(),
                        pizzaOrdered.getIsDeliveryOrder().isDeliveryOrder(),
                        pizzaOrdered.getDeliveryAddress().address(),
                        pizzaOrdered.getPizzas().stream().map(Pizza::getId).collect(Collectors.toList())
                ));
        logger.info("Event send for order: {}", pizzaOrdered.getOrderReference().orderReference().toString());
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
        logger.info("Information of order saved for order: {}", pizzaOrdered.getOrderReference().orderReference().toString());
    }
}
