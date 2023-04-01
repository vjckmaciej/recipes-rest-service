package recipes.domain;

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
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    private LocalDateTime localDateTime;
    @Column
    @ElementCollection
    private List<String> ingredients = new ArrayList<>();
    @Column
    @ElementCollection
    private List<String> directions = new ArrayList<>();
}
