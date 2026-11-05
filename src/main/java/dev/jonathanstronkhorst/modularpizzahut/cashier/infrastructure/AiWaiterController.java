package dev.jonathanstronkhorst.modularpizzahut.cashier.infrastructure;

import dev.jonathanstronkhorst.modularpizzahut.cashier.domain.order_of_pizzas.OrderOfPizzaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AI assignment: The AI Waiter.
 * <p>
 * An inbound adapter that turns a free-text order into an {@link AiOrderDraft} using an LLM,
 * and hands it to the (deterministic) domain via {@link OrderOfPizzaService}.
 */
@RestController
@RequestMapping("/order/chat")
public class AiWaiterController {
    private static final Logger logger = LoggerFactory.getLogger(AiWaiterController.class);
    private final ChatClient chatClient;
    private final OrderOfPizzaService orderOfPizzaService;

    public AiWaiterController(ChatClient.Builder chatClientBuilder, OrderOfPizzaService orderOfPizzaService) {
        this.chatClient = chatClientBuilder.build();
        this.orderOfPizzaService = orderOfPizzaService;
    }

    @PostMapping(consumes = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> chat(@RequestBody String message) {
        logger.info("The AI waiter received: {}", message);

        // TODO 1: Ask the LLM to turn the message into an AiOrderDraft, e.g.
        //         chatClient.prompt().system("You are a pizza waiter ...").user(message).call().entity(AiOrderDraft.class)

        // TODO 2: Expose the menu to the LLM with a @Tool annotated getMenu() method (see Pizza enum)
        //         and register it with .tools(this), so the LLM knows the pizza ids instead of guessing.

        // TODO 3: Map the draft to domain value objects (CustomerDetails, IsDeliveryOrder, DeliveryAddress,
        //         Pizza.getPizzasForIds) and call orderOfPizzaService.orderPizza(...).

        // TODO 4: Return 201 Created with the order reference, or 422 when the order was rejected
        //         (hint: a rejected order has no PizzaOrdered event).

        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body("The AI waiter is still in training. Implement me!");
    }
}
