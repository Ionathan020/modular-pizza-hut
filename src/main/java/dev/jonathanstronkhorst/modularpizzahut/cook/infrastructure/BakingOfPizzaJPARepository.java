package dev.jonathanstronkhorst.modularpizzahut.cook.infrastructure;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface BakingOfPizzaJPARepository extends CrudRepository<BakingOfPizzasDocument, UUID> {
    Optional<BakingOfPizzasDocument> findByOrderReference(UUID orderReference);
}
