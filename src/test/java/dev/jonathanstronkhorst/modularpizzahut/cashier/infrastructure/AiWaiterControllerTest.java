package dev.jonathanstronkhorst.modularpizzahut.cashier.infrastructure;

import dev.jonathanstronkhorst.modularpizzahut.cashier.domain.order_of_pizzas.OrderOfPizzaService;
import dev.jonathanstronkhorst.modularpizzahut.cashier.domain.order_of_pizzas.aggregate.order.CustomerDetails;
import dev.jonathanstronkhorst.modularpizzahut.cashier.domain.order_of_pizzas.aggregate.order.DeliveryAddress;
import dev.jonathanstronkhorst.modularpizzahut.cashier.domain.order_of_pizzas.aggregate.order.IsDeliveryOrder;
import dev.jonathanstronkhorst.modularpizzahut.cashier.domain.order_of_pizzas.aggregate.pizza.Pizza;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.Prompt;

import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * AI assignment step 5: remove {@code @Disabled} and make this test pass.
 * The fake {@link ChatModel} always answers with a fixed JSON draft, so no real LLM is called.
 */
@Disabled("AI assignment: remove me once you've implemented AiWaiterController")
class AiWaiterControllerTest {

    private static final String FIXED_DRAFT = """
            {
              "name": "Jan",
              "phoneNumber": "0612345678",
              "deliveryOrder": true,
              "address": "Main St 1",
              "pizzaIds": [1, 1, 4]
            }
            """;

    /** A fake model that ignores the prompt and always returns the same answer. */
    static class FakeChatModel implements ChatModel {
        private final String answer;

        FakeChatModel(String answer) {
            this.answer = answer;
        }

        @Override
        public ChatResponse call(Prompt prompt) {
            return new ChatResponse(List.of(new Generation(new AssistantMessage(answer))));
        }
    }

    @Test
    void shouldTurnFreeTextIntoAnOrder() {
        var orderOfPizzaService = mock(OrderOfPizzaService.class);
        var controller = new AiWaiterController(ChatClient.builder(new FakeChatModel(FIXED_DRAFT)), orderOfPizzaService);

        controller.chat("Two salamis and a veggie for Jan, 0612345678, deliver to Main St 1");

        verify(orderOfPizzaService).orderPizza(
                eq(CustomerDetails.of("Jan", "0612345678")),
                eq(IsDeliveryOrder.of(true)),
                eq(DeliveryAddress.of("Main St 1")),
                eq(List.of(Pizza.SALAMI, Pizza.SALAMI, Pizza.VEGGIE)));
    }
}
