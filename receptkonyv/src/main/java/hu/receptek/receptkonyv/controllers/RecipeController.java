package hu.receptek.receptkonyv.controllers;

import hu.receptek.receptkonyv.entities.Category;
import hu.receptek.receptkonyv.entities.Recipe;
import hu.receptek.receptkonyv.entities.Status;
import hu.receptek.receptkonyv.entities.User;
import hu.receptek.receptkonyv.repositories.RecipeRepository;
import hu.receptek.receptkonyv.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/recipes")
public class RecipeController {
    
    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public ResponseEntity<Iterable<Recipe>> getAll() {
        return ResponseEntity.ok (recipeRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> get(@PathVariable Integer id){
        Optional<Recipe> rec = recipeRepository.findById(id);
        if (rec.isPresent()){
            return ResponseEntity.ok(rec.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<Iterable<Recipe>> getAllByCategory(@PathVariable Category category){
        List<Recipe> ret = new ArrayList<>();
        for (Recipe recipe : recipeRepository.findAllByCategory(category)){
            if (recipe.getStatus().toString().equals("PUBLIC")){
                ret.add(recipe);
            }
        }
        return ResponseEntity.ok(ret);
    }

    @GetMapping("/recipes/own")
    public ResponseEntity<Iterable<Recipe>> getAllByAuthor(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User user = userRepository.findByUsername(name).get();
        return ResponseEntity.ok(recipeRepository.findAllByAuthor(user));
    }

    @Secured({ "ROLE_USER" , "ROLE_ADMIN"})
    @PostMapping("")
    public ResponseEntity<Recipe> insert(@RequestBody Recipe rec){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        rec.setImage("ownfood");
        //kiegészíteni!!
        rec.setAuthor(userRepository.findByUsername(name).get());
        return ResponseEntity.ok(recipeRepository.save(rec));
    }

    //admin törölhet, user csak sajátját
    @DeleteMapping("/{id}")
    public ResponseEntity<Recipe> delete(@PathVariable Integer id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<String> roles = auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        String username = auth.getName();
        Optional<Recipe> rec = recipeRepository.findById(id);
        if (rec.isPresent()){
            if(roles.contains("ROLE_ADMIN") || username.equals(rec.get().getAuthor().getUsername())){
                recipeRepository.deleteById(id);
                return ResponseEntity.ok().build();
            }else{
                return ResponseEntity.badRequest().build();
            }
        }else{
            return ResponseEntity.notFound().build();
        }
    }



    //admin, user a sajátját
    @PutMapping("/{id}")
    public ResponseEntity<Recipe> update(@PathVariable Integer id, @RequestBody Recipe recipe){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<String> roles = auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        String username = auth.getName();
        Optional<Recipe> rec = recipeRepository.findById(id);
        if(rec.isPresent()){
            if(roles.contains("ROLE_ADMIN") || username.equals(rec.get().getAuthor().getUsername())){
                recipe.setId(id);
                return ResponseEntity.ok(recipeRepository.save(recipe));
            }else{
                return ResponseEntity.badRequest().build();
            }
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
