package dev.jonathanstronkhorst.modularpizzahut.cook.infrastructure;

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



    @Override
    public Optional<BakingOfPizzas> findBakingOfPizzas(OrderReference orderReference) {
        return Optional.empty();
    }

    @Override
    public void sendEvent(PizzasBaked pizzasBaked) {

    }

    @Override
    public void save(PizzasBaked pizzasBaked) {

    }

    @Override
    public void saveInitialOrder(OrderReference orderReference, String name, String phoneNumber, boolean isDeliveryOrder, String address, java.util.List<Integer> pizzaIds) {

    }
}
