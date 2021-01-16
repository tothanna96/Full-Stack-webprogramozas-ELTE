package hu.receptek.receptkonyv.repositories;

import hu.receptek.receptkonyv.entities.Keyword;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeywordRepository extends CrudRepository<Keyword, Integer> {

}