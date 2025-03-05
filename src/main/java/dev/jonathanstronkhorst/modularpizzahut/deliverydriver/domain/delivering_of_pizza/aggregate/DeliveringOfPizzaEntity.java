package dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.aggregate;

import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.aggregate.order.CustomerDetails;
import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.aggregate.order.DeliveryAddress;
import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.aggregate.order.OrderReference;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
class DeliveringOfPizzaEntity {
    private final OrderReference orderReference;
    private final CustomerDetails customerDetails;
    private final DeliveryAddress deliveryAddress;

    public static DeliveringOfPizzaEntity of(OrderReference orderReference, CustomerDetails customerDetails, DeliveryAddress deliveryAddress) {
        return new DeliveringOfPizzaEntity(orderReference, customerDetails, deliveryAddress);
    }

}
