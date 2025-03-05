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
    private UUID orderReference;
    private String name;
    private String phoneNumber;
    private boolean isDeliveryOrder;
    private String address;
    private String pizzaIds;

    private OrderOfPizzaDocument(UUID orderReference,
                                 String name,
                                 String phoneNumber,
                                 boolean isDeliveryOrder,
                                 String address,
                                 List<Integer> pizzaIds) {
        this.orderReference = orderReference;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.isDeliveryOrder = isDeliveryOrder;
        this.address = address;
        setPizzaIds(pizzaIds);
    }

    public static OrderOfPizzaDocument of(UUID orderReference,
                                          String name,
                                          String phoneNumber,
                                          boolean isDeliveryOrder,
                                          String address,
                                          List<Integer> pizzaIds) {
        return new OrderOfPizzaDocument(orderReference, name, phoneNumber, isDeliveryOrder, address, pizzaIds);
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
