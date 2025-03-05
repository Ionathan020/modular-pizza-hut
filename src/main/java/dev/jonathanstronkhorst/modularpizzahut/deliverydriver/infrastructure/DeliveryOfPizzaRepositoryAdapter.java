package dev.jonathanstronkhorst.modularpizzahut.deliverydriver.infrastructure;




import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.DeliveringOfPizzaRepository;
import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.aggregate.DeliveringOfPizza;
import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.aggregate.order.CustomerDetails;
import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.aggregate.order.DeliveryAddress;
import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.aggregate.order.OrderReference;
import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.event.PizzaDelivered;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeliveryOfPizzaRepositoryAdapter implements DeliveringOfPizzaRepository {
    private final DeliveryOfPizzaJPARepository deliveryOfPizzaJPARepository;

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
        System.out.println("Pizza!");
    }

    @Override
    public void save(PizzaDelivered pizzaDelivered) {
        deliveryOfPizzaJPARepository.save(DeliveryOfPizzaDocument.of(
                pizzaDelivered.getOrderReference().orderReference(),
                pizzaDelivered.getCustomerDetails().name(),
                pizzaDelivered.getCustomerDetails().phoneNumber(),
                pizzaDelivered.getDeliveryAddress().address()));
        System.out.println("WHAT! NO TIP?!");
    }
}
