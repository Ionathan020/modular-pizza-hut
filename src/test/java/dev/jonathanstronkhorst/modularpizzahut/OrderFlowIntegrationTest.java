package dev.jonathanstronkhorst.modularpizzahut;

import dev.jonathanstronkhorst.modularpizzahut.cashier.domain.order_of_pizzas.OrderOfPizzaService;
import dev.jonathanstronkhorst.modularpizzahut.cashier.domain.order_of_pizzas.aggregate.order.CustomerDetails;
import dev.jonathanstronkhorst.modularpizzahut.cashier.domain.order_of_pizzas.aggregate.order.DeliveryAddress;
import dev.jonathanstronkhorst.modularpizzahut.cashier.domain.order_of_pizzas.aggregate.order.IsDeliveryOrder;
import dev.jonathanstronkhorst.modularpizzahut.cashier.domain.order_of_pizzas.aggregate.order.PizzaOrderResult;
import dev.jonathanstronkhorst.modularpizzahut.cashier.domain.order_of_pizzas.aggregate.pizza.Pizza;
import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.BakingOfPizzaService;
import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.DeliveringOfPizzaService;
import dev.jonathanstronkhorst.modularpizzahut.handout.domain.handout_of_pizza.HandoutOfPizzaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class OrderFlowIntegrationTest {

    @Autowired
    private OrderOfPizzaService orderOfPizzaService;

    @MockitoSpyBean
    private BakingOfPizzaService bakingOfPizzaService;

    @MockitoSpyBean
    private DeliveringOfPizzaService deliveringOfPizzaService;

    @MockitoSpyBean
    private HandoutOfPizzaService handoutOfPizzaService;

    @Test
    void shouldProcessOrderFromCashierToDelivery() {
        // Given
        CustomerDetails customerDetails = CustomerDetails.of("Delivery Test", "0600000001");
        IsDeliveryOrder isDeliveryOrder = IsDeliveryOrder.of(true);
        DeliveryAddress deliveryAddress = DeliveryAddress.of("Test Address");
        List<Pizza> pizzas = List.of(Pizza.MARGHERITA);

        // When
        PizzaOrderResult result = orderOfPizzaService.orderPizza(customerDetails, isDeliveryOrder, deliveryAddress, pizzas);

        // Then
        assertThat(result.getEvent()).isNotNull();
        
        // Wait for the asynchronous flow to complete
        await().atMost(5, TimeUnit.SECONDS).untilAsserted(() -> {
            verify(bakingOfPizzaService, atLeastOnce()).bakePizza(any());
            verify(deliveringOfPizzaService, atLeastOnce()).deliverPizza(any());
            verify(handoutOfPizzaService, never()).handoutPizza(any());
        });
    }

    @Test
    void shouldProcessOrderFromCashierToHandout() {
        // Given
        CustomerDetails customerDetails = CustomerDetails.of("Handout Test", "0600000002");
        IsDeliveryOrder isDeliveryOrder = IsDeliveryOrder.of(false);
        DeliveryAddress deliveryAddress = DeliveryAddress.of(null);
        List<Pizza> pizzas = List.of(Pizza.MARGHERITA);

        // When
        PizzaOrderResult result = orderOfPizzaService.orderPizza(customerDetails, isDeliveryOrder, deliveryAddress, pizzas);

        // Then
        assertThat(result.getEvent()).isNotNull();

        // Wait for the asynchronous flow to complete
        await().atMost(5, TimeUnit.SECONDS).untilAsserted(() -> {
            verify(bakingOfPizzaService, atLeastOnce()).bakePizza(any());
            verify(handoutOfPizzaService, atLeastOnce()).handoutPizza(any());
            verify(deliveringOfPizzaService, never()).deliverPizza(any());
        });
    }
}
