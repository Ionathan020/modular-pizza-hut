package dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.aggregate;

import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.aggregate.order.CustomerDetails;
import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.aggregate.order.DeliveryAddress;
import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.aggregate.order.OrderReference;
import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.aggregate.order.OrderStatus;
import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.aggregate.pizza.DeliveryOfPizzaResult;
import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.command.DeliverPizza;
import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.event.PizzaDelivered;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class DeliveringOfPizzaTest {

    @Test
    void shouldCreatePizzaDeliveredEventWhenHandlingDeliverPizzaCommand() {
        // Given
        OrderReference orderReference = OrderReference.of(UUID.randomUUID());
        CustomerDetails customerDetails = CustomerDetails.of("John Doe", "0612345678");
        DeliveryAddress deliveryAddress = DeliveryAddress.of("Street 1");
        DeliveringOfPizza deliveringOfPizza = DeliveringOfPizza.of(orderReference, customerDetails, deliveryAddress);
        DeliverPizza command = DeliverPizza.of(orderReference);

        // When
        DeliveryOfPizzaResult result = deliveringOfPizza.handleCommand(command);

        // Then
        assertThat(result.getOrderStatus()).isEqualTo(OrderStatus.DELIVERED);
        assertThat(result.getEvent()).isNotNull();
        assertThat(result.getEvent()).isInstanceOf(PizzaDelivered.class);
        PizzaDelivered event = (PizzaDelivered) result.getEvent();
        assertThat(event.getOrderReference()).isEqualTo(orderReference);
        assertThat(event.getOrderStatus()).isEqualTo(OrderStatus.DELIVERED);
        assertThat(event.getCustomerDetails()).isEqualTo(customerDetails);
        assertThat(event.getDeliveryAddress()).isEqualTo(deliveryAddress);
    }
}
