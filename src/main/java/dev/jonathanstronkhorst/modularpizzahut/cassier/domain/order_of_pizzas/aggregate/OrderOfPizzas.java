package dev.jonathanstronkhorst.modularpizzahut.cassier.domain.order_of_pizzas.aggregate;

import dev.jonathanstronkhorst.modularpizzahut.cassier.domain.order_of_pizzas.aggregate.order.*;
import dev.jonathanstronkhorst.modularpizzahut.cassier.domain.order_of_pizzas.aggregate.pizza.Pizza;
import dev.jonathanstronkhorst.modularpizzahut.cassier.domain.order_of_pizzas.command.OrderPizzas;
import dev.jonathanstronkhorst.modularpizzahut.cassier.domain.order_of_pizzas.event.PizzaOrdered;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderOfPizzas {
    private final OrderOfPizzasEntity orderOfPizzasEntity;

    public static OrderOfPizzas of(CustomerDetails customerDetails,
                                  IsDeliveryOrder isDeliveryOrder,
                                  DeliveryAddress deliveryAddress, List<Pizza> pizzas) {
        return new OrderOfPizzas(OrderOfPizzasEntity.of(customerDetails, isDeliveryOrder, deliveryAddress, pizzas));
    }

    public PizzaOrderResult handleCommand(OrderPizzas orderPizzas) {
        var orderReference =  new OrderReference(UUID.randomUUID());
        if (orderOfPizzasEntity.hasPizzaContainingPineapple()) {
            System.out.print("""
                               ⠀⠀⢀⣤⣤⣤⡀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⣿⠿⢿⣏⠙⢿⣦⡀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣤⣶⣶⣿⣇⡁⠈⢿⣧⡀⠹⣿⣦⡀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣸⡿⠃⢰⣿⡿⢿⣆⠀⠙⣿⣦⡈⠙⢿⣦⡀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣿⠃⠀⣿⡏⠁⠘⣿⣆⠀⠈⠻⣷⣄⠀⠙⢿⣦⡀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣿⠇⠀⠀⢸⣧⠀⠀⠘⢿⣆⠀⠀⠈⠻⣷⡄⠀⢻⣷
   ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⡿⠋⠀⠀⠀⢸⣿⡆⠀⠀⠈⢿⣧⠀⠀⠀⢹⣷⠀⠈⣿
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣾⠟⠁⠀⠀⠀⢀⣿⡟⣿⣆⠀⠀⠀⢻⣧⠀⠀⠀⣿⡆⢠⣿
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣾⡟⠁⠀⠀⠀⠀⢠⣾⠟⠁⠘⣿⡄⠀⠀⠈⣿⣇⠀⠀⢹⣇⢸⣿
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⡿⠋⠀⠀⠀⠀⠀⣠⣿⣏⣠⣤⣤⣿⡇⠀⠀⠀⠸⣿⠀⠀⠘⣿⢸⣿
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣼⡟⠁⠀⠀⠀⠠⡶⠟⠛⠛⠋⠉⠉⠉⢹⣿⠀⠀⠀⠀⣿⡇⠀⠀⢿⢸⣿
⠀⠀⠀⠀⠀ ⠀⠀⠀⠀⠀⠀⠀⠀⣾⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣀⣈⣿⡀⠀⠀⠀⠸⠧⠀⠀⠀⣿⡟
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⡟⠀⠀⠀⠀⠀⠀⠀⠀⣀⣤⣶⡿⠿⠛⠛⠉⢻⣇⠀⠀⠀⠀⠀⠀⠀⢠⣿⠁
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⣿⠁⠀⠀⠀⠀⠀⢀⣴⡾⠟⠉⠀⠀⠀⠀⠀⠀⠀⠛⠀⠀⠀⠀⠀⠀⠀⣼⡟⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⡇⠀⠀⠀⠀⠀⣠⡾⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣼⡿⠁⠀
⠀⠀⠀⠀⠀⠀⠀⠀⣰⣿⠁⠀⠀⠀⠀⣰⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣾⠟⠀⠀⠀
    ⠀⢀⣼⡟⠁⠀⠀⠀⠀⠀⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣤⣾⠟⠁⠀⠀⠀⠀
    ⣴⡿⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⣶⡿⠛⠁⠀⠀⠀⠀⠀⠀
    ⠹⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣠⣴⣾⠿⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀
              ⠀⠀⠀⠀⠀⢀⣠⣴⡾⠿⠛⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣴⡿⠛⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⠻⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                    """);
            return PizzaOrderResult.of(
                    orderReference,
                    OrderStatus.NEW);
        }
        return PizzaOrderResult.of(
                orderReference,
                PizzaOrdered.of(orderReference, OrderStatus.NEW, orderOfPizzasEntity.getIsDeliveryOrder(), orderOfPizzasEntity.getPizzas()),
                OrderStatus.NEW);
    }

}
