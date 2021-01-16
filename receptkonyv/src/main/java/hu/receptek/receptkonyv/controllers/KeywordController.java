package hu.receptek.receptkonyv.controllers;

import hu.receptek.receptkonyv.entities.Keyword;
import hu.receptek.receptkonyv.entities.Recipe;
import hu.receptek.receptkonyv.repositories.KeywordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/keywords")
public class KeywordController {
    @Autowired
    KeywordRepository keywordRepository;

    @Secured({ "ROLE_USER" , "ROLE_ADMIN"})
    @GetMapping("")
    public ResponseEntity<Iterable<Keyword>> getAll() {
        return ResponseEntity.ok (keywordRepository.findAll());
    }

    @Secured({ "ROLE_USER" , "ROLE_ADMIN"})
    @GetMapping("/{id}")
    public ResponseEntity<Keyword> get(@PathVariable Integer id){
        Optional<Keyword> kw = keywordRepository.findById(id);
        if (kw.isPresent()){
            return ResponseEntity.ok(kw.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @Secured({ "ROLE_USER" , "ROLE_ADMIN"})
    @PostMapping("")
    public ResponseEntity<Keyword> insert(@RequestBody Keyword kw){
        return ResponseEntity.ok(keywordRepository.save(kw));
    }

    @Secured({ "ROLE_ADMIN" })
    @DeleteMapping("/{id}")
    public ResponseEntity<Keyword> delete(@PathVariable Integer id){
        Optional<Keyword> kw = keywordRepository.findById(id);
        if (kw.isPresent()){
            keywordRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @Secured({ "ROLE_USER" , "ROLE_ADMIN"})
    @PutMapping("/{id}")
    public ResponseEntity<Keyword> update(@PathVariable Integer id, @RequestBody Keyword keyword){
        Optional<Keyword> ke = keywordRepository.findById(id);
        if(ke.isPresent()){
            keyword.setId(id);
            return ResponseEntity.ok(keywordRepository.save(keyword));
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
