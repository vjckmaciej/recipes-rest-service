package recipes.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeDTO {
//    @JsonIgnore
//    private Long id;
    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotBlank(message = "Description cannot be blank")
    private String description;
    @NotBlank
    private String category;
    @NotNull
    @Size(min = 1, message = "There must be at least 1 ingredient for recipe")
    private List<String> ingredients;
    @NotNull
    @Size(min = 1, message = "There must be at least 1 direction for recipe")
    private List<String> directions;


}
