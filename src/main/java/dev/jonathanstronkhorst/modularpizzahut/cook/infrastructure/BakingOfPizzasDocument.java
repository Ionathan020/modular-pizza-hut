package dev.jonathanstronkhorst.modularpizzahut.cook.infrastructure;

import jakarta.persistence.*;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="pizzaorders")
public class BakingOfPizzasDocument {

    @Id
    @GeneratedValue
    private int id;
    private UUID orderReference;
    private boolean isDeliveryOrder;
    private String pizzaIds;

    private BakingOfPizzasDocument(UUID orderReference, boolean isDeliveryOrder, List<Integer> pizzaIds) {
        this.orderReference = orderReference;
        this.isDeliveryOrder = isDeliveryOrder;
        setPizzaIds(pizzaIds);
    }

    public static BakingOfPizzasDocument of(UUID orderReference, boolean isDeliveryOrder, List<Integer> pizzaIds) {
        return new BakingOfPizzasDocument(orderReference, isDeliveryOrder, pizzaIds);
    }

    public List<Integer> getPizzaIds() {
        return Arrays.stream(pizzaIds.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public void setPizzaIds(List<Integer> pizzaIds) {
        this.pizzaIds = pizzaIds.stream().map(String::valueOf)
                .collect(Collectors.joining(","));
    }
}
