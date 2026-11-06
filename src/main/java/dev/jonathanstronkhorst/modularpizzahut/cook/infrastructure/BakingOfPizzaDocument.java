package dev.jonathanstronkhorst.modularpizzahut.cook.infrastructure;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "bakedpizzaorders")
public class BakingOfPizzaDocument {
    @Id
    private UUID orderReference;
    private String name;
    private String phoneNumber;
    private boolean isDeliveryOrder;
    private String address;
    private String pizzaIds;
    private String orderStatus;

    public BakingOfPizzaDocument() {
    }

    private BakingOfPizzaDocument(UUID orderReference,
                                  String name,
                                  String phoneNumber,
                                  boolean isDeliveryOrder,
                                  String address,
                                  List<Integer> pizzaIds,
                                  String orderStatus) {
        this.orderReference = orderReference;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.isDeliveryOrder = isDeliveryOrder;
        this.address = address;
        setPizzaIds(pizzaIds);
        this.orderStatus = orderStatus;
    }

    public UUID getOrderReference() {
        return orderReference;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean isDeliveryOrder() {
        return isDeliveryOrder;
    }

    public String getAddress() {
        return address;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public static BakingOfPizzaDocument of(UUID orderReference,
                                           String name,
                                           String phoneNumber,
                                           boolean isDeliveryOrder,
                                           String address,
                                           List<Integer> pizzaIds,
                                           String orderStatus) {
        return new BakingOfPizzaDocument(orderReference, name, phoneNumber, isDeliveryOrder, address, pizzaIds, orderStatus);
    }

    public List<Integer> getPizzaIds() {
        if (pizzaIds == null || pizzaIds.isBlank()) {
            return List.of();
        }
        return Arrays.stream(pizzaIds.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private void setPizzaIds(List<Integer> pizzaIds) {
        this.pizzaIds = pizzaIds.stream().map(String::valueOf).collect(Collectors.joining(","));
    }
}