package dev.jonathanstronkhorst.modularpizzahut.cashier.infrastructure;

import dev.jonathanstronkhorst.modularpizzahut.cashier.domain.order_of_pizzas.OrderOfPizzaService;
import dev.jonathanstronkhorst.modularpizzahut.cashier.domain.order_of_pizzas.aggregate.order.CustomerDetails;
import dev.jonathanstronkhorst.modularpizzahut.cashier.domain.order_of_pizzas.aggregate.order.DeliveryAddress;
import dev.jonathanstronkhorst.modularpizzahut.cashier.domain.order_of_pizzas.aggregate.order.IsDeliveryOrder;
import dev.jonathanstronkhorst.modularpizzahut.cashier.domain.order_of_pizzas.aggregate.order.OrderStatus;
import dev.jonathanstronkhorst.modularpizzahut.cashier.domain.order_of_pizzas.aggregate.pizza.Pizza;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/order", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class OrderOfPizzasController {
    private static final Logger logger = LoggerFactory.getLogger(OrderOfPizzasController.class);
    private final OrderOfPizzaService orderOfPizzaService;

    public OrderOfPizzasController(OrderOfPizzaService orderOfPizzaService) {
        this.orderOfPizzaService = orderOfPizzaService;
    }

    @PostMapping
    public ResponseEntity<Void> orderPizzas(@RequestBody OrderOfPizzaRequest orderOfPizzaRequest) {
        logger.info("Recieved new order!");
        var pizzaOrdered = orderOfPizzaService.orderPizza(
                CustomerDetails.of(orderOfPizzaRequest.getName(), orderOfPizzaRequest.getPhoneNumber()),
                IsDeliveryOrder.of(orderOfPizzaRequest.isDeliveryOrder()),
                DeliveryAddress.of(orderOfPizzaRequest.getAddress()),
                Pizza.getPizzasForIds(orderOfPizzaRequest.getPizzas()));
        if (pizzaOrdered.getOrderStatus() == OrderStatus.NEW) {
            return ResponseEntity.created(URI.create("/order/" + pizzaOrdered.getOrderReference().orderReference().toString())).build();
        }
        logger.info("Processing new order");
        return ResponseEntity.unprocessableEntity().build();
    }
}
