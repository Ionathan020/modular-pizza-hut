package dev.jonathanstronkhorst.modularpizzahut.cashier.domain.order_of_pizzas.aggregate;

import dev.jonathanstronkhorst.modularpizzahut.cashier.domain.order_of_pizzas.aggregate.order.*;
import dev.jonathanstronkhorst.modularpizzahut.cashier.domain.order_of_pizzas.aggregate.pizza.Pizza;
import dev.jonathanstronkhorst.modularpizzahut.cashier.domain.order_of_pizzas.command.OrderPizzas;
import dev.jonathanstronkhorst.modularpizzahut.cashier.domain.order_of_pizzas.event.PizzaOrdered;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class OrderOfPizzasTest {

    @Test
    void shouldCreatePizzaOrderedEventWhenNoPineappleIsPresent() {
        // Given
        CustomerDetails customerDetails = CustomerDetails.of("John Doe", "0612345678");
        IsDeliveryOrder isDeliveryOrder = IsDeliveryOrder.of(true);
        DeliveryAddress deliveryAddress = DeliveryAddress.of("Street 1");
        List<Pizza> pizzas = List.of(Pizza.MARGHERITA, Pizza.SALAMI);
        OrderOfPizzas orderOfPizzas = OrderOfPizzas.of(customerDetails, isDeliveryOrder, deliveryAddress, pizzas);
        OrderPizzas command = OrderPizzas.of(customerDetails, isDeliveryOrder, deliveryAddress, pizzas);

        // When
        PizzaOrderResult result = orderOfPizzas.handleCommand(command);

        // Then
        assertThat(result.getOrderStatus()).isEqualTo(OrderStatus.NEW);
        assertThat(result.getEvent()).isNotNull();
        assertThat(result.getEvent()).isInstanceOf(PizzaOrdered.class);
        PizzaOrdered event = (PizzaOrdered) result.getEvent();
        assertThat(event.getPizzas()).containsExactly(Pizza.MARGHERITA, Pizza.SALAMI);
    }

    @Test
    void shouldNotCreateEventWhenPineappleIsPresent() {
        // Given
        CustomerDetails customerDetails = CustomerDetails.of("John Doe", "0612345678");
        IsDeliveryOrder isDeliveryOrder = IsDeliveryOrder.of(true);
        DeliveryAddress deliveryAddress = DeliveryAddress.of("Street 1");
        List<Pizza> pizzas = List.of(Pizza.HAWAI);
        OrderOfPizzas orderOfPizzas = OrderOfPizzas.of(customerDetails, isDeliveryOrder, deliveryAddress, pizzas);
        OrderPizzas command = OrderPizzas.of(customerDetails, isDeliveryOrder, deliveryAddress, pizzas);

        // When
        PizzaOrderResult result = orderOfPizzas.handleCommand(command);

        // Then
        assertThat(result.getOrderStatus()).isEqualTo(OrderStatus.NEW);
        assertThat(result.getEvent()).isNull();
    }
}
