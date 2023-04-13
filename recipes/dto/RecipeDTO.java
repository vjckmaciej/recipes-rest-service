package recipes.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import recipes.domain.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeDTO {
    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotBlank(message = "Description cannot be blank")
    private String description;
    @NotBlank
    private String category;
    private LocalDateTime date;
    @NotNull
    @Size(min = 1, message = "There must be at least 1 ingredient for recipe")
    private List<String> ingredients;
    @NotNull
    @Size(min = 1, message = "There must be at least 1 direction for recipe")
    private List<String> directions;
    @JsonIgnore
    private User user;
}
