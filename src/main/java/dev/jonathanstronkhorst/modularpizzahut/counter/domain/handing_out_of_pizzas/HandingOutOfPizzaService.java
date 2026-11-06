package dev.jonathanstronkhorst.modularpizzahut.counter.domain.handing_out_of_pizzas;

import dev.jonathanstronkhorst.modularpizzahut.counter.domain.handing_out_of_pizzas.aggregate.HandingOutOfPizza;
import dev.jonathanstronkhorst.modularpizzahut.counter.domain.handing_out_of_pizzas.aggregate.order.OrderReference;
import dev.jonathanstronkhorst.modularpizzahut.counter.domain.handing_out_of_pizzas.command.HandOutPizza;
import org.springframework.stereotype.Service;

@Service
public class HandingOutOfPizzaService {
    private final HandingOutOfPizzaRepository handingOutOfPizzaRepository;

    public HandingOutOfPizzaService(HandingOutOfPizzaRepository handingOutOfPizzaRepository) {
        this.handingOutOfPizzaRepository = handingOutOfPizzaRepository;
    }

    public void handOutPizza(OrderReference orderReference) {
        HandingOutOfPizza handingOutOfPizza = handingOutOfPizzaRepository.findHandingOutOfPizza(orderReference).orElse(null);
        if (handingOutOfPizza == null) {
            return;
        }
        var pizzaHandedOut = handingOutOfPizza.handleCommand(HandOutPizza.of(orderReference));
        pizzaHandedOut.publish(handingOutOfPizzaRepository);
        pizzaHandedOut.save(handingOutOfPizzaRepository);
    }
}