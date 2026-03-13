package dev.jonathanstronkhorst.modularpizzahut.cassier.infrastructure;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import dev.jonathanstronkhorst.modularpizzahut.cassier.domain.order_of_pizzas.OrderOfPizzaService;
import dev.jonathanstronkhorst.modularpizzahut.cassier.domain.order_of_pizzas.aggregate.order.CustomerDetails;
import dev.jonathanstronkhorst.modularpizzahut.cassier.domain.order_of_pizzas.aggregate.order.DeliveryAddress;
import dev.jonathanstronkhorst.modularpizzahut.cassier.domain.order_of_pizzas.aggregate.order.IsDeliveryOrder;
import dev.jonathanstronkhorst.modularpizzahut.cassier.domain.order_of_pizzas.aggregate.order.PizzaOrderResult;
import dev.jonathanstronkhorst.modularpizzahut.cassier.domain.order_of_pizzas.aggregate.pizza.Pizza;
import java.util.ArrayList;

@Route("")
public class PizzaOrderView extends VerticalLayout {

    public PizzaOrderView(OrderOfPizzaService orderOfPizzaService) {
        TextField name = new TextField("Customer Name");
        TextField phoneNumber = new TextField("Phone Number");
        Checkbox isDelivery = new Checkbox("Delivery Order");
        TextField address = new TextField("Delivery Address");
        address.setVisible(false);

        isDelivery.addValueChangeListener(event -> address.setVisible(event.getValue()));

        MultiSelectComboBox<Pizza> pizzaSelection = new MultiSelectComboBox<>("Select Pizzas");
        pizzaSelection.setItems(Pizza.values());
        pizzaSelection.setItemLabelGenerator(Pizza::getName);

        Button orderButton = new Button("Order Pizza", event -> {
            try {
                PizzaOrderResult result = orderOfPizzaService.orderPizza(
                        CustomerDetails.of(name.getValue(), phoneNumber.getValue()),
                        IsDeliveryOrder.of(isDelivery.getValue()),
                        DeliveryAddress.of(address.getValue()),
                        new ArrayList<>(pizzaSelection.getValue())
                );
                Notification.show("Order placed! Reference: " + result.getOrderReference().orderReference());
            } catch (Exception e) {
                Notification.show("Error: " + e.getMessage());
            }
        });

        add(name, phoneNumber, isDelivery, address, pizzaSelection, orderButton);
    }
}
