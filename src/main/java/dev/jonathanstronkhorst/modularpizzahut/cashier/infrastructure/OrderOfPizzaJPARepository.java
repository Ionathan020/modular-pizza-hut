package dev.jonathanstronkhorst.modularpizzahut.cashier.infrastructure;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderOfPizzaJPARepository extends JpaRepository<OrderOfPizzaDocument, Long> {
    Optional<OrderOfPizzaDocument> findByOrderReference(UUID orderReference);
}
