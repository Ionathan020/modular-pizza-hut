package dev.jonathanstronkhorst.modularpizzahut.handout.domain.handout_of_pizza.aggregate;

import dev.jonathanstronkhorst.modularpizzahut.handout.domain.handout_of_pizza.aggregate.order.OrderReference;
import dev.jonathanstronkhorst.modularpizzahut.handout.domain.handout_of_pizza.aggregate.order.OrderStatus;
import dev.jonathanstronkhorst.modularpizzahut.handout.domain.handout_of_pizza.aggregate.pizza.HandoutOfPizzaResult;
import dev.jonathanstronkhorst.modularpizzahut.handout.domain.handout_of_pizza.command.HandoutPizza;
import dev.jonathanstronkhorst.modularpizzahut.handout.domain.handout_of_pizza.event.PizzaHandedOut;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class HandoutOfPizzaTest {

    @Test
    void shouldCreatePizzaHandedOutEventWhenHandlingHandoutPizzaCommand() {
        // Given
        OrderReference orderReference = OrderReference.of(UUID.randomUUID());
        HandoutOfPizza handoutOfPizza = HandoutOfPizza.of(orderReference);
        HandoutPizza command = HandoutPizza.of(orderReference);

        // When
        HandoutOfPizzaResult result = handoutOfPizza.handleCommand(command);

        // Then
        assertThat(result.getOrderStatus()).isEqualTo(OrderStatus.HANDED_OUT);
        assertThat(result.getEvent()).isNotNull();
        assertThat(result.getEvent()).isInstanceOf(PizzaHandedOut.class);
        PizzaHandedOut event = (PizzaHandedOut) result.getEvent();
        assertThat(event.getOrderReference()).isEqualTo(orderReference);
        assertThat(event.getOrderStatus()).isEqualTo(OrderStatus.HANDED_OUT);
    }
}
