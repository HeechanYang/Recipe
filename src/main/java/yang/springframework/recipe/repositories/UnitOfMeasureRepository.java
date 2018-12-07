package yang.springframework.recipe.repositories;

import org.springframework.data.repository.CrudRepository;
import yang.springframework.recipe.models.UnitOfMeasure;

import java.util.Optional;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {
    Optional<UnitOfMeasure> findByDescription(String description);
}
