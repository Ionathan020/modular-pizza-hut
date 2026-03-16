package dev.jonathanstronkhorst.modularpizzahut.handout.infrastructure;

import dev.jonathanstronkhorst.modularpizzahut.handout.domain.handout_of_pizza.HandoutOfPizzaRepository;
import dev.jonathanstronkhorst.modularpizzahut.handout.domain.handout_of_pizza.aggregate.HandoutOfPizza;
import dev.jonathanstronkhorst.modularpizzahut.handout.domain.handout_of_pizza.aggregate.order.OrderReference;
import dev.jonathanstronkhorst.modularpizzahut.handout.domain.handout_of_pizza.event.PizzaHandedOut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class HandoutOfPizzaRepositoryAdapter implements HandoutOfPizzaRepository {
    private static final Logger logger = LoggerFactory.getLogger(HandoutOfPizzaRepositoryAdapter.class);
    private final HandoutOfPizzaJPARepository handoutOfPizzaJPARepository;

    public HandoutOfPizzaRepositoryAdapter(HandoutOfPizzaJPARepository handoutOfPizzaJPARepository) {
        this.handoutOfPizzaJPARepository = handoutOfPizzaJPARepository;
    }

    @Override
    public Optional<HandoutOfPizza> findHandoutOfPizza(OrderReference orderReference) {
        Optional<HandoutOfPizzaDocument> optionalDocument = handoutOfPizzaJPARepository.findByOrderReference(orderReference.orderReference());
        if (optionalDocument.isPresent()) {
            return Optional.of(HandoutOfPizza.of(OrderReference.of(optionalDocument.get().getOrderReference())));
        }
        return Optional.empty();
    }

    @Override
    public void sendEvent(PizzaHandedOut pizzaHandedOut) {
        logger.info("Pizza handed out for order: {}", pizzaHandedOut.getOrderReference().orderReference());
    }

    @Override
    public void save(PizzaHandedOut pizzaHandedOut) {
        HandoutOfPizzaDocument document = handoutOfPizzaJPARepository.findByOrderReference(pizzaHandedOut.getOrderReference().orderReference())
                .orElse(HandoutOfPizzaDocument.of(pizzaHandedOut.getOrderReference().orderReference()));
        handoutOfPizzaJPARepository.save(document);
    }
}
