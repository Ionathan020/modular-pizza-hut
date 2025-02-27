package dev.jonathanstronkhorst.modularpizzahut.cook.infrastructure;

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
public class BakingOfPizzasDocument {

    @Setter(AccessLevel.NONE)
    @Id
    private ObjectId _id;
    private UUID orderReference;
    private boolean isDeliveryOrder;
    private List<Integer> pizzaIds;
    @Version
    private Long version;

    private BakingOfPizzasDocument(UUID orderReference, boolean isDeliveryOrder, List<Integer> pizzaIds) {
        this.orderReference = orderReference;
        this.isDeliveryOrder = isDeliveryOrder;
        this.pizzaIds = pizzaIds;
    }

    public static BakingOfPizzasDocument of(UUID orderReference, boolean isDeliveryOrder, List<Integer> pizzaIds) {
        return new BakingOfPizzasDocument(orderReference, isDeliveryOrder, pizzaIds);
    }
}
