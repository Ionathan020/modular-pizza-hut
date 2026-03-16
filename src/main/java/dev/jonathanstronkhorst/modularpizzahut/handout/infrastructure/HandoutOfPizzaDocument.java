package dev.jonathanstronkhorst.modularpizzahut.handout.infrastructure;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;

@Entity
@Table(name="pizzaorders")
public class HandoutOfPizzaDocument {
    @Id
    private UUID orderReference;
    private String name;
    private String phoneNumber;
    private boolean isDeliveryOrder;
    private String address;
    private String pizzaIds;

    public HandoutOfPizzaDocument() {
    }

    private HandoutOfPizzaDocument(UUID orderReference) {
        this.orderReference = orderReference;
    }

    public UUID getOrderReference() {
        return orderReference;
    }

    public void setOrderReference(UUID orderReference) {
        this.orderReference = orderReference;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isDeliveryOrder() {
        return isDeliveryOrder;
    }

    public void setDeliveryOrder(boolean deliveryOrder) {
        isDeliveryOrder = deliveryOrder;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPizzaIds() {
        return pizzaIds;
    }

    public void setPizzaIds(String pizzaIds) {
        this.pizzaIds = pizzaIds;
    }

    public static HandoutOfPizzaDocument of(UUID orderReference) {
        return new HandoutOfPizzaDocument(orderReference);
    }
}
