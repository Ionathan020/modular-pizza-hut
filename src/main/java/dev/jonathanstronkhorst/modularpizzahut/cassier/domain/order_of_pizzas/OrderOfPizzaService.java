package dev.jonathanstronkhorst.modularpizzahut.cassier.domain.order_of_pizzas;

import dev.jonathanstronkhorst.modularpizzahut.cassier.domain.order_of_pizzas.aggregate.OrderOfPizzas;
import dev.jonathanstronkhorst.modularpizzahut.cassier.domain.order_of_pizzas.aggregate.order.CustomerDetails;
import dev.jonathanstronkhorst.modularpizzahut.cassier.domain.order_of_pizzas.aggregate.order.DeliveryAddress;
import dev.jonathanstronkhorst.modularpizzahut.cassier.domain.order_of_pizzas.aggregate.order.IsDeliveryOrder;
import dev.jonathanstronkhorst.modularpizzahut.cassier.domain.order_of_pizzas.aggregate.order.PizzaOrderResult;
import dev.jonathanstronkhorst.modularpizzahut.cassier.domain.order_of_pizzas.aggregate.pizza.Pizza;
import dev.jonathanstronkhorst.modularpizzahut.cassier.domain.order_of_pizzas.command.OrderPizzas;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderOfPizzaService {
    private final OrderOfPizzaProvider orderOfPizzaProvider;
    private final OrderOfPizzaRepository orderOfPizzaRepository;

    public PizzaOrderResult orderPizza(CustomerDetails customerDetails,
                                   IsDeliveryOrder isDeliveryOrder,
                                   DeliveryAddress deliveryAddress,
                                   List<Pizza> pizzas) {
        System.out.println("Ordering pizza's");
        OrderOfPizzas orderOfPizzas = orderOfPizzaProvider.newOrderOfPizzas(customerDetails, isDeliveryOrder, deliveryAddress, pizzas);
        PizzaOrderResult pizzaOrderResult = orderOfPizzas.handleCommand(OrderPizzas.of(customerDetails, isDeliveryOrder, deliveryAddress, pizzas));
        if (pizzaOrderResult.getEvent() != null) {
            pizzaOrderResult.getEvent().save(orderOfPizzaRepository);
            pizzaOrderResult.getEvent().publish(orderOfPizzaRepository);
        }
        System.out.println("Pizza's ordered with reference: " + pizzaOrderResult.getOrderReference().orderReference().toString());
        return pizzaOrderResult;
    }
}
