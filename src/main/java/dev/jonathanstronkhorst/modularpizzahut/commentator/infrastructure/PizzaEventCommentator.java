package dev.jonathanstronkhorst.modularpizzahut.commentator.infrastructure;

import dev.jonathanstronkhorst.modularpizzahut.cashier.SpringPizzaOrderedEvent;
import dev.jonathanstronkhorst.modularpizzahut.cook.SpringPizzasBakedEvent;
import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.SpringPizzaDeliveredEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.scheduling.annotation.Async;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class PizzaEventCommentator {
    private static final Logger logger = LoggerFactory.getLogger(PizzaEventCommentator.class);
    private static final String FALLBACK_TICKER_LINE = "The pizza news desk is waiting for its next delicious update.";

    private final ChatClient chatClient;

    public PizzaEventCommentator(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @Async
    @EventListener
    public void onPizzaOrdered(SpringPizzaOrderedEvent event) {
        commentAbout("A pizza order was placed for " + event.getName() + ", order " + event.getOrderReference() + ".");
    }

    @Async
    @EventListener
    public void onPizzasBaked(SpringPizzasBakedEvent event) {
        commentAbout("The pizzas for order " + event.getOrderReference() + " just came out of the oven.");
    }

    @Async
    @EventListener
    public void onPizzaDelivered(SpringPizzaDeliveredEvent event) {
        commentAbout("Order " + event.getOrderReference() + " has been delivered to " + event.getName() + ".");
    }

    String generateTickerLine(String eventDescription) {
        try {
            String tickerLine = chatClient.prompt()
                    .user("Write one short, upbeat pizzeria news ticker line about this event: " + eventDescription)
                    .call()
                    .content();
            return tickerLine == null || tickerLine.isBlank() ? FALLBACK_TICKER_LINE : tickerLine.strip();
        } catch (Exception exception) {
            return FALLBACK_TICKER_LINE;
        }
    }

    private void commentAbout(String eventDescription) {
        logger.info("Pizza ticker: {}", generateTickerLine(eventDescription));
    }
}