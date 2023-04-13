package recipes.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "RECIPES")
public class Recipe {
    @JsonIgnore
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recipe_seq")
    @SequenceGenerator(name = "recipe_seq", sequenceName = "recipe_sequence", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private String category;
    @Column
    @UpdateTimestamp
    private LocalDateTime date;
    @Column
    @ElementCollection
    private List<String> ingredients = new ArrayList<>();
    @Column
    @ElementCollection
    private List<String> directions = new ArrayList<>();
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
