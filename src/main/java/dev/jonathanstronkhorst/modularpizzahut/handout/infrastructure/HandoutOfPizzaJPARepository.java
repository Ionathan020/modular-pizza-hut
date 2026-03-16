package dev.jonathanstronkhorst.modularpizzahut.handout.infrastructure;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HandoutOfPizzaJPARepository extends JpaRepository<HandoutOfPizzaDocument, Long> {
    Optional<HandoutOfPizzaDocument> findByOrderReference(UUID orderReference);
}
