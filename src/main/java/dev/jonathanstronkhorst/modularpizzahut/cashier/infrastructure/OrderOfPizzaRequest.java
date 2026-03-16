package dev.jonathanstronkhorst.modularpizzahut.cashier.infrastructure;

import java.util.List;

public class OrderOfPizzaRequest {
    private String name;
    private String phoneNumber;
    private boolean isDeliveryOrder;
    private String address;
    private List<Integer> pizzas;

    public OrderOfPizzaRequest() {
    }

    public OrderOfPizzaRequest(String name, String phoneNumber, boolean isDeliveryOrder, String address, List<Integer> pizzas) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.isDeliveryOrder = isDeliveryOrder;
        this.address = address;
        this.pizzas = pizzas;
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

    public List<Integer> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<Integer> pizzas) {
        this.pizzas = pizzas;
    }
}
