package hu.receptek.receptkonyv.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "folder")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Folder {
    
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;
   
   @Column(nullable = false)
   private String foldertitle;
   
   @ManyToOne
   @JoinColumn
   @JsonIgnore
   private User author;
   
   @OneToMany(mappedBy = "folder")
   private List<Recipe> recipe;

   
}
