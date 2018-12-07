package yang.springframework.recipe.repositories;

import org.springframework.data.repository.CrudRepository;
import yang.springframework.recipe.models.Category;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    Optional<Category> findByDescription(String description);
}
