package dev.jonathanstronkhorst.modularpizzahut.cassier.infrastructure;

import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderOfPizzaJPARepository extends CrudRepository<OrderOfPizzaDocument, UUID> {
}
