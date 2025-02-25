package dev.jonathanstronkhorst.modularpizzahut.cassier.infrastructure;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderOfPizzaRequest {
    private String name;
    private String phoneNumber;
    private boolean isDeliveryOrder;
    private String address;
    private List<Integer> pizzas;
}
