package dev.jonathanstronkhorst.modularpizzahut.cashier.domain.order_of_pizzas.aggregate.order;


public record CustomerDetails(String name, String phoneNumber) {
    public static CustomerDetails of(String name, String phoneNumber) {
        return new CustomerDetails(name, phoneNumber);
    }
}
