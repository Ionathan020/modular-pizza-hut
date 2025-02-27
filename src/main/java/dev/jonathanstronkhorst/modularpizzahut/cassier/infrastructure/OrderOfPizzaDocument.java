package dev.jonathanstronkhorst.modularpizzahut.cassier.infrastructure;

import java.util.List;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "pizza_order")
public class OrderOfPizzaDocument {
    @Setter(AccessLevel.NONE)
    @Id
    private ObjectId _id;
    private UUID orderReference;
    private List<Integer> pizzaIds;
    @Version
    private Long version;

    private OrderOfPizzaDocument(UUID orderReference, List<Integer> pizzaIds) {
        this.orderReference = orderReference;
        this.pizzaIds = pizzaIds;
    }

    public static OrderOfPizzaDocument of(UUID orderReference, List<Integer> pizzaIds) {
        return new OrderOfPizzaDocument(orderReference, pizzaIds);
    }

}
