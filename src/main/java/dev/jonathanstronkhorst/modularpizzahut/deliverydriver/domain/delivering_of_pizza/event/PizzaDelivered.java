package dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.event;

import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.BaseDeliveringOfPizzaEvent;
import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.DeliveringOfPizzaRepository;
import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.aggregate.order.CustomerDetails;
import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.aggregate.order.DeliveryAddress;
import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.aggregate.order.OrderReference;
import dev.jonathanstronkhorst.modularpizzahut.deliverydriver.domain.delivering_of_pizza.aggregate.order.OrderStatus;

public class PizzaDelivered implements BaseDeliveringOfPizzaEvent {
    private final OrderReference orderReference;
    private final OrderStatus orderStatus;
    private final CustomerDetails customerDetails;
    private final DeliveryAddress deliveryAddress;

    private PizzaDelivered(OrderReference orderReference,
                           OrderStatus orderStatus,
                           CustomerDetails customerDetails,
                           DeliveryAddress deliveryAddress) {
        this.orderReference = orderReference;
        this.orderStatus = orderStatus;
        this.customerDetails = customerDetails;
        this.deliveryAddress = deliveryAddress;
    }

    public static PizzaDelivered of(OrderReference orderReference,
                                    OrderStatus orderStatus,
                                    CustomerDetails customerDetails,
                                    DeliveryAddress deliveryAddress) {
        return new PizzaDelivered(orderReference, orderStatus, customerDetails, deliveryAddress);
    }

    public OrderReference getOrderReference() {
        return orderReference;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public CustomerDetails getCustomerDetails() {
        return customerDetails;
    }

    public DeliveryAddress getDeliveryAddress() {
        return deliveryAddress;
    }

    @Override
    public void publish(DeliveringOfPizzaRepository bakingOfPizzaRepository) {
        bakingOfPizzaRepository.sendEvent(this);
    }

    @Override
    public void save(DeliveringOfPizzaRepository bakingOfPizzaRepository) {
        bakingOfPizzaRepository.save(this);
    }
}
