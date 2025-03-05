package dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza;


import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.aggregate.DeliveringOfPizza;
import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.aggregate.order.OrderReference;
import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.aggregate.pizza.DeliveryOfPizzaResult;
import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.command.DeliverPizza;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeliveringOfPizzaService {
    private final DeliveringOfPizzaRepository deliveringOfPizzaRepository;

    public DeliveryOfPizzaResult deliverPizza(OrderReference orderReference) {
        System.out.println("Nog even wachten!");
        DeliveringOfPizza deliveringOfPizza = getDeliveringOfPizza(orderReference);
        if (deliveringOfPizza == null) {
            return DeliveryOfPizzaResult.of(null, null, null);
        }
        DeliveryOfPizzaResult deliveryOfPizzaResult = deliveringOfPizza.handleCommand(DeliverPizza.of(orderReference));
        if (deliveryOfPizzaResult.getEvent() != null) {
            deliveryOfPizzaResult.getEvent().publish(deliveringOfPizzaRepository);
            deliveryOfPizzaResult.getEvent().save(deliveringOfPizzaRepository);
        }
        return deliveryOfPizzaResult;
    }

    private DeliveringOfPizza getDeliveringOfPizza(OrderReference orderReference) {
        return deliveringOfPizzaRepository.findDeliveringOfPizza(orderReference).orElse(null);
    }
}
