package dev.jonathanstronkhorst.modularpizzahut.deliverydriver.infrastructure;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveringOfPizzaJPARepository extends JpaRepository<DeliveringOfPizzaDocument, UUID> {
    Optional<DeliveringOfPizzaDocument> findByOrderReference(UUID orderReference);
}