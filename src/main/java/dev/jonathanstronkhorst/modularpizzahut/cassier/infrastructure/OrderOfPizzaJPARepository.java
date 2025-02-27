package dev.jonathanstronkhorst.modularpizzahut.cassier.infrastructure;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderOfPizzaJPARepository extends CrudRepository<OrderOfPizzaDocument, UUID> {
    Optional<OrderOfPizzaDocument> findByOrderReference(UUID orderReference);
}
