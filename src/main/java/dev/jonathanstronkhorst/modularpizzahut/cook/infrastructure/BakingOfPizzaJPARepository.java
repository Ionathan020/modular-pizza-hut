package dev.jonathanstronkhorst.modularpizzahut.cook.infrastructure;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BakingOfPizzaJPARepository extends JpaRepository<BakingOfPizzaDocument, UUID> {
    Optional<BakingOfPizzaDocument> findByOrderReference(UUID orderReference);
}