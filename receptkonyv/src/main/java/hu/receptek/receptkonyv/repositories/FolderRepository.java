package hu.receptek.receptkonyv.repositories;

import hu.receptek.receptkonyv.entities.Folder;
import org.springframework.data.repository.CrudRepository;

public interface FolderRepository extends CrudRepository<Folder, Integer> {
    
}
