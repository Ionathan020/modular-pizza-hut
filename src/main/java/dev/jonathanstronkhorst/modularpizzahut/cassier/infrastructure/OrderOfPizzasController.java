package dev.jonathanstronkhorst.modularpizzahut.cassier.infrastructure;

import dev.jonathanstronkhorst.modularpizzahut.cassier.domain.order_of_pizzas.OrderOfPizzaService;
import dev.jonathanstronkhorst.modularpizzahut.cassier.domain.order_of_pizzas.aggregate.order.CustomerDetails;
import dev.jonathanstronkhorst.modularpizzahut.cassier.domain.order_of_pizzas.aggregate.order.DeliveryAddress;
import dev.jonathanstronkhorst.modularpizzahut.cassier.domain.order_of_pizzas.aggregate.order.IsDeliveryOrder;
import dev.jonathanstronkhorst.modularpizzahut.cassier.domain.order_of_pizzas.aggregate.order.OrderStatus;
import dev.jonathanstronkhorst.modularpizzahut.cassier.domain.order_of_pizzas.aggregate.pizza.Pizza;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/order", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class OrderOfPizzasController {
    private final OrderOfPizzaService orderOfPizzaService;

    @PostMapping
    public ResponseEntity<Void> orderPizzas(@RequestBody OrderOfPizzaRequest orderOfPizzaRequest) {
        var pizzaOrdered = orderOfPizzaService.orderPizza(
                CustomerDetails.of(orderOfPizzaRequest.getName(), orderOfPizzaRequest.getPhoneNumber()),
                IsDeliveryOrder.of(orderOfPizzaRequest.isDeliveryOrder()),
                DeliveryAddress.of(orderOfPizzaRequest.getAddress()),
                Pizza.getPizzasForIds(orderOfPizzaRequest.getPizzas()));
        if (pizzaOrdered.getOrderStatus() == OrderStatus.NEW) {
            return ResponseEntity.created(URI.create("/order/" + pizzaOrdered.getOrderReference().orderReference().toString())).build();
        }
        return ResponseEntity.unprocessableEntity().build();
    }
}
