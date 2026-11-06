package dev.jonathanstronkhorst.modularpizzahut.deliverydriver.infrastructure;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "deliveringpizzaorders")
public class DeliveringOfPizzaDocument {
    @Id
    private UUID orderReference;
    private String name;
    private String phoneNumber;
    private String address;
    private String orderStatus;

    public DeliveringOfPizzaDocument() {
    }

    private DeliveringOfPizzaDocument(UUID orderReference, String name, String phoneNumber, String address, String orderStatus) {
        this.orderReference = orderReference;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public static DeliveringOfPizzaDocument of(UUID orderReference,
                                               String name,
                                               String phoneNumber,
                                               String address,
                                               String orderStatus) {
        return new DeliveringOfPizzaDocument(orderReference, name, phoneNumber, address, orderStatus);
    }
}