package hu.receptek.receptkonyv.controllers;

import hu.receptek.receptkonyv.entities.Folder;
import hu.receptek.receptkonyv.entities.Keyword;
import hu.receptek.receptkonyv.repositories.FolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/folders")
public class FolderController {
    
    @Autowired
    FolderRepository folderRepository;

    @Secured({ "ROLE_USER" , "ROLE_ADMIN"})
    @GetMapping("")
    public ResponseEntity<Iterable<Folder>> getAll() {
        return ResponseEntity.ok (folderRepository.findAll());
    }

    @Secured({ "ROLE_USER" , "ROLE_ADMIN"})
    @GetMapping("/{id}")
    public ResponseEntity<Folder> get(@PathVariable Integer id){
        Optional<Folder> fr = folderRepository.findById(id);
        if (fr.isPresent()){
            return ResponseEntity.ok(fr.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @Secured({ "ROLE_USER" , "ROLE_ADMIN"})
    @PostMapping("")
    public ResponseEntity<Folder> insert(@RequestBody Folder fd){
        return ResponseEntity.ok(folderRepository.save(fd));
    }

    @Secured({ "ROLE_USER" , "ROLE_ADMIN"})
    @DeleteMapping("/{id}")
    public ResponseEntity<Keyword> delete(@PathVariable Integer id){
        Optional<Folder> fd = folderRepository.findById(id);
        if (fd.isPresent()){
            folderRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @Secured({ "ROLE_USER" , "ROLE_ADMIN"})
    @PutMapping("/{id}")
    public ResponseEntity<Folder> update(@PathVariable Integer id, @RequestBody Folder folder){
        Optional<Folder> f = folderRepository.findById(id);
        if(f.isPresent()){
            folder.setId(id);
            return ResponseEntity.ok(folderRepository.save(folder));
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
