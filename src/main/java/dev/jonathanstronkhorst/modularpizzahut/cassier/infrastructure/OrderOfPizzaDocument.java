package dev.jonathanstronkhorst.modularpizzahut.cassier.infrastructure;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name="pizzaorders")
public class OrderOfPizzaDocument {
    @Id
    private UUID orderReference;
    private String name;
    private String phoneNumber;
    private boolean isDeliveryOrder;
    private String address;
    private String pizzaIds;

    public OrderOfPizzaDocument() {
    }

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
