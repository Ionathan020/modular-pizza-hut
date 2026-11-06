package dev.jonathanstronkhorst.modularpizzahut.commentator.infrastructure;

import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.Prompt;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PizzaEventCommentatorTest {

    @Test
    void shouldReturnTheGeneratedTickerLine() {
        PizzaEventCommentator commentator = new PizzaEventCommentator(ChatClientTestBuilder.builder("Fresh from the oven!"));

        String tickerLine = commentator.generateTickerLine("An order was baked.");

        assertThat(tickerLine).isEqualTo("Fresh from the oven!");
    }

    @Test
    void shouldReturnFallbackTickerLineWhenTheModelFails() {
        PizzaEventCommentator commentator = new PizzaEventCommentator(ChatClientTestBuilder.failingBuilder());

        String tickerLine = commentator.generateTickerLine("An order was delivered.");

        assertThat(tickerLine).isEqualTo("The pizza news desk is waiting for its next delicious update.");
    }

    private static class ChatClientTestBuilder {
        private static org.springframework.ai.chat.client.ChatClient.Builder builder(String answer) {
            return org.springframework.ai.chat.client.ChatClient.builder(new StubChatModel(answer, false));
        }

        private static org.springframework.ai.chat.client.ChatClient.Builder failingBuilder() {
            return org.springframework.ai.chat.client.ChatClient.builder(new StubChatModel(null, true));
        }
    }

    private static class StubChatModel implements ChatModel {
        private final String answer;
        private final boolean fail;

        private StubChatModel(String answer, boolean fail) {
            this.answer = answer;
            this.fail = fail;
        }

        @Override
        public ChatResponse call(Prompt prompt) {
            if (fail) {
                throw new IllegalStateException("Model unavailable");
            }
            return new ChatResponse(List.of(new Generation(new AssistantMessage(answer))));
        }
    }
}