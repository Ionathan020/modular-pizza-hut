package dev.jonathanstronkhorst.modularpizzahut.deliverydriver.infrastructure;

import jakarta.persistence.*;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="pizzaorders")
public class DeliveryOfPizzaDocument {
    @Id
    private UUID orderReference;
    private String name;
    private String phoneNumber;
    private boolean isDeliveryOrder;
    private String address;
    private String pizzaIds;

    private DeliveryOfPizzaDocument(UUID orderReference, String name, String phoneNumber, String address) {
        this.orderReference = orderReference;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.isDeliveryOrder = true;
        this.address = address;
    }

    public static DeliveryOfPizzaDocument of(UUID orderReference, String name, String phoneNumber, String address) {
        return new DeliveryOfPizzaDocument(orderReference, name, phoneNumber, address);
    }
}
