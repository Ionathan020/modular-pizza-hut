package dev.jonathanstronkhorst.modularpizzahut.cassier.domain.order_of_pizzas.aggregate.pizza;

import dev.jonathanstronkhorst.modularpizzahut.cassier.domain.order_of_pizzas.aggregate.pizza.ingredients.*;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public enum Pizza {
    SALAMI(1, "Salami", List.of(
            new Salami(25),
            new Cheese(10)
    )),
    HAWAI(2, "Hawai", List.of(
            new Ham(30),
            new Pineapple(5),
            new Cheese(8)));

    final int id;
    final String name;
    final List<Ingredient> ingredients;

    Pizza(int id, String name, List<Ingredient> ingredients) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
    }

    public boolean containsPineapple() {
        for (Ingredient ingredient : ingredients) {
            if (ingredient instanceof Pineapple) {
                return true;
            }
        }
        return false;
    }

    public static Pizza getPizzaForId(Integer id) {
        for (Pizza pizza : Pizza.values()) {
            if (pizza.getId() == id) {
                return pizza;
            }
        }
        return null;
    }

    public static List<Pizza> getPizzasForIds(List<Integer> pizzaIds) {
        var pizzas = new ArrayList<Pizza>();
        for (Integer pizzaId : pizzaIds) {
            Pizza pizza = getPizzaForId(pizzaId);
            if (pizza != null) {
                pizzas.add(pizza);
            }
        }
        return pizzas;
    }

}
