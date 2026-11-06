package dev.jonathanstronkhorst.modularpizzahut.deliverydriver.infrastructure;

import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.DeliveringOfPizzaRepository;
import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.aggregate.DeliveringOfPizza;
import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.aggregate.order.CustomerDetails;
import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.aggregate.order.DeliveryAddress;
import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.aggregate.order.OrderReference;
import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.event.PizzaDelivered;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class DeliveringOfPizzaRepositoryAdapter implements DeliveringOfPizzaRepository {
    private final DeliveringOfPizzaJPARepository deliveringOfPizzaJPARepository;
    private final SpringPizzaDeliveredEventPublisher springPizzaDeliveredEventPublisher;

    public DeliveringOfPizzaRepositoryAdapter(DeliveringOfPizzaJPARepository deliveringOfPizzaJPARepository,
                                              SpringPizzaDeliveredEventPublisher springPizzaDeliveredEventPublisher) {
        this.deliveringOfPizzaJPARepository = deliveringOfPizzaJPARepository;
        this.springPizzaDeliveredEventPublisher = springPizzaDeliveredEventPublisher;
    }

    @Override
    public Optional<DeliveringOfPizza> findDeliveringOfPizza(OrderReference orderReference) {
        return deliveringOfPizzaJPARepository.findByOrderReference(orderReference.orderReference())
                .map(document -> DeliveringOfPizza.of(
                        OrderReference.of(document.getOrderReference()),
                        CustomerDetails.of(document.getName(), document.getPhoneNumber()),
                        DeliveryAddress.of(document.getAddress())));
    }

    @Override
    public void saveInitialOrder(OrderReference orderReference, CustomerDetails customerDetails, DeliveryAddress deliveryAddress) {
        deliveringOfPizzaJPARepository.save(DeliveringOfPizzaDocument.of(
                orderReference.orderReference(),
                customerDetails.name(),
                customerDetails.phoneNumber(),
                deliveryAddress.address(),
                "READY_FOR_DELIVERY"));
    }

    @Override
    public void sendEvent(PizzaDelivered pizzaDelivered) {
        springPizzaDeliveredEventPublisher.publish(new dev.jonathanstronkhorst.modularpizzahut.deliverydriver.SpringPizzaDeliveredEvent(
                springPizzaDeliveredEventPublisher,
                pizzaDelivered.getOrderReference().orderReference(),
                pizzaDelivered.getCustomerDetails().name(),
                pizzaDelivered.getCustomerDetails().phoneNumber(),
                pizzaDelivered.getDeliveryAddress().address()));
    }

    @Override
    public void save(PizzaDelivered pizzaDelivered) {
        DeliveringOfPizzaDocument document = deliveringOfPizzaJPARepository.findByOrderReference(
                        pizzaDelivered.getOrderReference().orderReference())
                .orElseThrow(() -> new IllegalStateException("No delivery order found for "
                        + pizzaDelivered.getOrderReference().orderReference()));
        document.setOrderStatus(pizzaDelivered.getOrderStatus().name());
        deliveringOfPizzaJPARepository.save(document);
    }
}
