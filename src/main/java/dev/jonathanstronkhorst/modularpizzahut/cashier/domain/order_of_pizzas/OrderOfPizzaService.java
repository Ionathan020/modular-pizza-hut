package dev.jonathanstronkhorst.modularpizzahut.cashier.domain.order_of_pizzas;

import dev.jonathanstronkhorst.modularpizzahut.cashier.domain.order_of_pizzas.aggregate.OrderOfPizzas;
import dev.jonathanstronkhorst.modularpizzahut.cashier.domain.order_of_pizzas.aggregate.order.CustomerDetails;
import dev.jonathanstronkhorst.modularpizzahut.cashier.domain.order_of_pizzas.aggregate.order.DeliveryAddress;
import dev.jonathanstronkhorst.modularpizzahut.cashier.domain.order_of_pizzas.aggregate.order.IsDeliveryOrder;
import dev.jonathanstronkhorst.modularpizzahut.cashier.domain.order_of_pizzas.aggregate.order.PizzaOrderResult;
import dev.jonathanstronkhorst.modularpizzahut.cashier.domain.order_of_pizzas.aggregate.pizza.Pizza;
import dev.jonathanstronkhorst.modularpizzahut.cashier.domain.order_of_pizzas.command.OrderPizzas;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class OrderOfPizzaService {
    private static final Logger logger = LoggerFactory.getLogger(OrderOfPizzaService.class);
    private final OrderOfPizzaProvider orderOfPizzaProvider;
    private final OrderOfPizzaRepository orderOfPizzaRepository;

    public OrderOfPizzaService(OrderOfPizzaProvider orderOfPizzaProvider, OrderOfPizzaRepository orderOfPizzaRepository) {
        this.orderOfPizzaProvider = orderOfPizzaProvider;
        this.orderOfPizzaRepository = orderOfPizzaRepository;
    }

    public PizzaOrderResult orderPizza(CustomerDetails customerDetails,
                                   IsDeliveryOrder isDeliveryOrder,
                                   DeliveryAddress deliveryAddress,
                                   List<Pizza> pizzas) {
        logger.info("Ordering pizza's");
        OrderOfPizzas orderOfPizzas = orderOfPizzaProvider.newOrderOfPizzas(customerDetails, isDeliveryOrder, deliveryAddress, pizzas);
        PizzaOrderResult pizzaOrderResult = orderOfPizzas.handleCommand(OrderPizzas.of(customerDetails, isDeliveryOrder, deliveryAddress, pizzas));
        if (pizzaOrderResult.getEvent() != null) {
            pizzaOrderResult.getEvent().save(orderOfPizzaRepository);
            pizzaOrderResult.getEvent().publish(orderOfPizzaRepository);
        }
        logger.info("Pizza's ordered with reference: {}", pizzaOrderResult.getOrderReference().orderReference().toString());
        return pizzaOrderResult;
    }
}
