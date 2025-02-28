package dev.jonathanstronkhorst.modularpizzahut.cook.infrastructure;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BakingOfPizzaJPARepository extends JpaRepository<BakingOfPizzasDocument, Long> {
    Optional<BakingOfPizzasDocument> findByOrderReference(UUID orderReference);
}
