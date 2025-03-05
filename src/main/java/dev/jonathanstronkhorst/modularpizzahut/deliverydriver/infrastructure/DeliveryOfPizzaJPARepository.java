package dev.jonathanstronkhorst.modularpizzahut.deliverydriver.infrastructure;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryOfPizzaJPARepository  extends JpaRepository<DeliveryOfPizzaDocument, Long> {
    Optional<DeliveryOfPizzaDocument> findByOrderReference(UUID orderReference);
}
