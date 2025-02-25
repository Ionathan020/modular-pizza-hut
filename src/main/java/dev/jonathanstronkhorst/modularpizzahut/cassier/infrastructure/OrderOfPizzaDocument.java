package dev.jonathanstronkhorst.modularpizzahut.cassier.infrastructure;

import java.util.List;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@Document
public class OrderOfPizzaDocument {
    @Setter(AccessLevel.NONE)
    @Id
    private ObjectId _id;
    private UUID orderReference;
    List<Integer> pizzaIds;
}
