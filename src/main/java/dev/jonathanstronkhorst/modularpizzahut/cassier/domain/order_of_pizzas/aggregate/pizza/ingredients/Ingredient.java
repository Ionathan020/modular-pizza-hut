package dev.jonathanstronkhorst.modularpizzahut.cassier.domain.order_of_pizzas.aggregate.pizza.ingredients;

public class Ingredient {
    private final int quantity;

    public Ingredient(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }
}
