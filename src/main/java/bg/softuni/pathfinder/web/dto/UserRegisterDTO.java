package bg.softuni.pathfinder.web.dto;

import bg.softuni.pathfinder.model.Level;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRegisterDTO {
    @NotBlank
    @Size(min = 2)
    private String username;

    @NotEmpty
    @Size(min = 5)
    private String fullName;

    @Email
    private String email;

    @Min(0)
    @Max(90)
    private Integer age;

    @Size(min = 5)
    private String password;

    private String confirmPassword;

    private Level level;
}
