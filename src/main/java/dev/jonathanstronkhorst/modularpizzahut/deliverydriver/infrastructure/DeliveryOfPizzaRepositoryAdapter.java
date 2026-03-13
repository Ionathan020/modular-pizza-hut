package dev.jonathanstronkhorst.modularpizzahut.deliverydriver.infrastructure;




import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.DeliveringOfPizzaRepository;
import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.aggregate.DeliveringOfPizza;
import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.aggregate.order.CustomerDetails;
import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.aggregate.order.DeliveryAddress;
import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.aggregate.order.OrderReference;
import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.event.PizzaDelivered;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class DeliveryOfPizzaRepositoryAdapter implements DeliveringOfPizzaRepository {
    private static final Logger logger = LoggerFactory.getLogger(DeliveryOfPizzaRepositoryAdapter.class);
    private final DeliveryOfPizzaJPARepository deliveryOfPizzaJPARepository;

    public DeliveryOfPizzaRepositoryAdapter(DeliveryOfPizzaJPARepository deliveryOfPizzaJPARepository) {
        this.deliveryOfPizzaJPARepository = deliveryOfPizzaJPARepository;
    }

    @Override
    public Optional<DeliveringOfPizza> findDeliveringOfPizza(OrderReference orderReference) {
        Optional<DeliveryOfPizzaDocument> optionalDeliveryOfPizzaDocument = deliveryOfPizzaJPARepository.findByOrderReference(orderReference.orderReference());
        if(optionalDeliveryOfPizzaDocument.isPresent()) {
            DeliveryOfPizzaDocument deliveryOfPizzaDocument = optionalDeliveryOfPizzaDocument.get();
            return Optional.of(DeliveringOfPizza.of(
                    OrderReference.of(deliveryOfPizzaDocument.getOrderReference()),
                    CustomerDetails.of(deliveryOfPizzaDocument.getName(), deliveryOfPizzaDocument.getPhoneNumber()),
                    DeliveryAddress.of(deliveryOfPizzaDocument.getAddress())
            ));
        }
        return Optional.empty();
    }

    @Override
    public void sendEvent(PizzaDelivered pizzaDelivered) {
        logger.info("Pizza!");
    }

    @Override
    public void save(PizzaDelivered pizzaDelivered) {
        deliveryOfPizzaJPARepository.save(DeliveryOfPizzaDocument.of(
                pizzaDelivered.getOrderReference().orderReference(),
                pizzaDelivered.getCustomerDetails().name(),
                pizzaDelivered.getCustomerDetails().phoneNumber(),
                pizzaDelivered.getDeliveryAddress().address()));
        logger.info("WHAT! NO TIP?!");
    }
}
