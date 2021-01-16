package hu.receptek.receptkonyv.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "recipe")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Recipe {
    
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;
   
   @Column(nullable = false)
   private String title;
   
   @Column
   private String image;
    
   @Column(nullable = false, length=1000)
   private String ingredients;
    
   @Column(nullable = false, length=1500)
   private String instructions;

   @Column(nullable = false)
   @Enumerated(EnumType.STRING)
   private Category category;

   @Column(nullable = false)
   @Enumerated(EnumType.STRING)
   private Status status;
    
   //átírni a logikáját
   @Enumerated(EnumType.STRING)
   private Rating rating;


   @ManyToOne
   @JoinColumn
   @JsonIgnore
   private User author;

   @ManyToMany
   @JoinTable
   private List<Keyword> keywords;
   
   @ManyToOne
   @JoinColumn
   @JsonIgnore
   private Folder folder;

}
