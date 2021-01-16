package hu.receptek.receptkonyv.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class User {
    
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;
   
   @Column(nullable = false, unique = true)
   private String username;
    
   @Column(nullable = false, unique = true)
   private String email;
    
   @Column(nullable = false)
   private String password;

   @Column
   private boolean enabled;
   
   @Column(nullable = false)
   @Enumerated(EnumType.STRING)
   private Role role;

   //Egy felhasználó több recept
   //egy-sok kapcsolat kialakítása a Recipe el
   @OneToMany(mappedBy = "author")
   private List<Recipe> recipes;
   
   @OneToMany(mappedBy = "author")
   private List<Folder> folder;
   
}
