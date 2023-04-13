package recipes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;

    @NotBlank
    @Pattern(regexp = ".+@.+\\..+", message = "Email must match the email pattern.")
    private String email;

    @NotBlank
    @Length(min = 8, message = "Password must have 8 characters at least.")
    private String password;
}
