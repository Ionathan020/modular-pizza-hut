package dev.jonathanstronkhorst.modularpizzahut.counter.domain.handing_out_of_pizzas.aggregate;

import dev.jonathanstronkhorst.modularpizzahut.counter.domain.handing_out_of_pizzas.aggregate.order.OrderReference;
import dev.jonathanstronkhorst.modularpizzahut.counter.domain.handing_out_of_pizzas.command.HandOutPizza;
import dev.jonathanstronkhorst.modularpizzahut.counter.domain.handing_out_of_pizzas.event.PizzaHandedOut;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class HandingOutOfPizzaTest {

    @Test
    void shouldCreatePizzaHandedOutEventWhenHandlingHandOutPizzaCommand() {
        OrderReference orderReference = OrderReference.of(UUID.randomUUID());
        HandingOutOfPizza handingOutOfPizza = HandingOutOfPizza.of(orderReference);

        PizzaHandedOut event = handingOutOfPizza.handleCommand(HandOutPizza.of(orderReference));

        assertThat(event.getOrderReference()).isEqualTo(orderReference);
    }
}