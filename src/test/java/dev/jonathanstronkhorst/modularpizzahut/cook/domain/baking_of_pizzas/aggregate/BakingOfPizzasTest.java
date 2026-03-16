package dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.aggregate;

import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.aggregate.order.IsDeliveryOrder;
import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.aggregate.order.OrderReference;
import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.aggregate.order.OrderStatus;
import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.aggregate.pizza.BakedPizzaResult;
import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.aggregate.pizza.Pizza;
import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.command.BakePizzas;
import dev.jonathanstronkhorst.modularpizzahut.cook.domain.baking_of_pizzas.event.PizzasBaked;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class BakingOfPizzasTest {

    @Test
    void shouldCreatePizzasBakedEventWhenHandlingBakePizzasCommand() {
        // Given
        OrderReference orderReference = OrderReference.of(UUID.randomUUID());
        IsDeliveryOrder isDeliveryOrder = IsDeliveryOrder.of(true);
        List<Pizza> pizzas = List.of(Pizza.MARGHERITA);
        BakingOfPizzas bakingOfPizzas = BakingOfPizzas.of(orderReference, isDeliveryOrder, pizzas);
        BakePizzas command = BakePizzas.of(orderReference, OrderStatus.BAKED);

        // When
        BakedPizzaResult result = bakingOfPizzas.handleCommand(command);

        // Then
        assertThat(result.getOrderStatus()).isEqualTo(OrderStatus.BAKED);
        assertThat(result.getEvent()).isNotNull();
        assertThat(result.getEvent()).isInstanceOf(PizzasBaked.class);
        PizzasBaked event = (PizzasBaked) result.getEvent();
        assertThat(event.getOrderReference()).isEqualTo(orderReference);
        assertThat(event.getOrderStatus()).isEqualTo(OrderStatus.BAKED);
        assertThat(event.getPizzas()).isEqualTo(pizzas);
    }
}
