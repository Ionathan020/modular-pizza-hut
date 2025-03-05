package dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.aggregate;

import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.aggregate.order.CustomerDetails;
import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.aggregate.order.DeliveryAddress;
import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.aggregate.order.OrderReference;
import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.aggregate.order.OrderStatus;
import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.aggregate.pizza.DeliveryOfPizzaResult;
import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.command.DeliverPizza;
import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.event.PizzaDelivered;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeliveringOfPizza {
    private final DeliveringOfPizzaEntity deliveringOfPizzaEntity;

    public static DeliveringOfPizza of(OrderReference orderReference, CustomerDetails customerDetails, DeliveryAddress deliveryAddress) {
        return new DeliveringOfPizza(DeliveringOfPizzaEntity.of(orderReference, customerDetails, deliveryAddress));
    }

    public DeliveryOfPizzaResult handleCommand(DeliverPizza deliverPizza) {
        return DeliveryOfPizzaResult.of(deliveringOfPizzaEntity.getOrderReference(),
                PizzaDelivered.of(deliveringOfPizzaEntity.getOrderReference(), OrderStatus.DELIVERED,
                        deliveringOfPizzaEntity.getCustomerDetails(), deliveringOfPizzaEntity.getDeliveryAddress()),
                OrderStatus.DELIVERED);
    }
}
