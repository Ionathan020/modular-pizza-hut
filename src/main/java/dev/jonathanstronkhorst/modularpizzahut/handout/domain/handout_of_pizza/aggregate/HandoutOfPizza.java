package dev.jonathanstronkhorst.modularpizzahut.handout.domain.handout_of_pizza.aggregate;

import dev.jonathanstronkhorst.modularpizzahut.handout.domain.handout_of_pizza.aggregate.order.OrderReference;
import dev.jonathanstronkhorst.modularpizzahut.handout.domain.handout_of_pizza.aggregate.order.OrderStatus;
import dev.jonathanstronkhorst.modularpizzahut.handout.domain.handout_of_pizza.aggregate.pizza.HandoutOfPizzaResult;
import dev.jonathanstronkhorst.modularpizzahut.handout.domain.handout_of_pizza.command.HandoutPizza;
import dev.jonathanstronkhorst.modularpizzahut.handout.domain.handout_of_pizza.event.PizzaHandedOut;

public class HandoutOfPizza {
    private final HandoutOfPizzaEntity handoutOfPizzaEntity;

    public HandoutOfPizza(HandoutOfPizzaEntity handoutOfPizzaEntity) {
        this.handoutOfPizzaEntity = handoutOfPizzaEntity;
    }

    public static HandoutOfPizza of(OrderReference orderReference) {
        return new HandoutOfPizza(HandoutOfPizzaEntity.of(orderReference));
    }

    public HandoutOfPizzaResult handleCommand(HandoutPizza handoutPizza) {
        return HandoutOfPizzaResult.of(handoutOfPizzaEntity.getOrderReference(),
                PizzaHandedOut.of(handoutOfPizzaEntity.getOrderReference(), OrderStatus.HANDED_OUT),
                OrderStatus.HANDED_OUT);
    }
}
