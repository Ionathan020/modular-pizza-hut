package dev.jonathanstronkhorst.modularpizzahut.cashier.infrastructure;

import java.util.List;

/**
 * Structured output the LLM should produce from a free-text order.
 * Intentionally lives in infrastructure: it is an adapter DTO, not a domain object.
 */
public record AiOrderDraft(String name,
                           String phoneNumber,
                           boolean deliveryOrder,
                           String address,
                           List<Integer> pizzaIds) {
}
