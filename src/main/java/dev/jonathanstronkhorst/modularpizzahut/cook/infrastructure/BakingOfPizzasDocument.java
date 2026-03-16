package dev.jonathanstronkhorst.modularpizzahut.cook.infrastructure;

import jakarta.persistence.*;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name="pizzaorders")
public class BakingOfPizzasDocument {

    @Id
    private UUID orderReference;
    private String name;
    private String phoneNumber;
    private boolean isDeliveryOrder;
    private String address;
    private String pizzaIds;

    public BakingOfPizzasDocument() {
    }

    private BakingOfPizzasDocument(UUID orderReference, boolean isDeliveryOrder, List<Integer> pizzaIds) {
        this.orderReference = orderReference;
        this.isDeliveryOrder = isDeliveryOrder;
        setPizzaIds(pizzaIds);
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

    public String getRawPizzaIds() {
        return pizzaIds;
    }

    public void setRawPizzaIds(String pizzaIds) {
        this.pizzaIds = pizzaIds;
    }

    public static BakingOfPizzasDocument of(UUID orderReference, String name, String phoneNumber, boolean isDeliveryOrder, String address, List<Integer> pizzaIds) {
        BakingOfPizzasDocument document = new BakingOfPizzasDocument(orderReference, isDeliveryOrder, pizzaIds);
        document.setName(name);
        document.setPhoneNumber(phoneNumber);
        document.setAddress(address);
        return document;
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
