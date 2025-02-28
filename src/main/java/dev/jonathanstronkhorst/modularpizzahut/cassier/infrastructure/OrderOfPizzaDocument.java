package dev.jonathanstronkhorst.modularpizzahut.cassier.infrastructure;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
public class OrderOfPizzaDocument {
    @Id
    @GeneratedValue
    private int id;
    private UUID orderReference;
    private boolean isDeliveryOrder;
    private String pizzaIds;

    private OrderOfPizzaDocument(UUID orderReference, boolean isDeliveryOrder, List<Integer> pizzaIds) {
        this.orderReference = orderReference;
        this.isDeliveryOrder = isDeliveryOrder;
        setPizzaIds(pizzaIds);
    }

    public static OrderOfPizzaDocument of(UUID orderReference, boolean isDeliveryOrder, List<Integer> pizzaIds) {
        return new OrderOfPizzaDocument(orderReference, isDeliveryOrder, pizzaIds);
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
