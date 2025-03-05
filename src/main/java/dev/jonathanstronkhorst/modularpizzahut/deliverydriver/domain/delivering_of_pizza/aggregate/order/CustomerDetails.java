package dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.aggregate.order;


public record CustomerDetails(String name, String phoneNumber) {
    public static CustomerDetails of(String name, String phoneNumber) {
        return new CustomerDetails(name, phoneNumber);
    }
}
