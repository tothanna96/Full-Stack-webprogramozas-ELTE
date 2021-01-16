package hu.receptek.receptkonyv.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "keyword")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Keyword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    //egy recepthez több kulcsszó, pl. desszert, tejmentes stb, több recepthez is mehet ugyanaz
    @ManyToMany(mappedBy = "keywords")
    @JsonIgnore
    private List<Recipe> recipes;
}
