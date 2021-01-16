package hu.receptek.receptkonyv.repositories;

import hu.receptek.receptkonyv.entities.Category;
import hu.receptek.receptkonyv.entities.Recipe;
import hu.receptek.receptkonyv.entities.Status;
import hu.receptek.receptkonyv.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Integer>{

    List<Recipe> findAllByCategory(Category category);
    List<Recipe> findAllByAuthor(User user);

}
