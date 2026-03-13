package dev.jonathanstronkhorst.modularpizzahut.deliverydriver.infrastructure;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name="pizzaorders")
public class DeliveryOfPizzaDocument {
    @Id
    private UUID orderReference;
    private String name;
    private String phoneNumber;
    private boolean isDeliveryOrder;
    private String address;
    private String pizzaIds;

    public DeliveryOfPizzaDocument() {
    }

    private DeliveryOfPizzaDocument(UUID orderReference, String name, String phoneNumber, String address) {
        this.orderReference = orderReference;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.isDeliveryOrder = true;
        this.address = address;
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

    public static DeliveryOfPizzaDocument of(UUID orderReference, String name, String phoneNumber, String address) {
        return new DeliveryOfPizzaDocument(orderReference, name, phoneNumber, address);
    }
}
