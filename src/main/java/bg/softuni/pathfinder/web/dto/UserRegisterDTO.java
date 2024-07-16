package bg.softuni.pathfinder.web.dto;

import bg.softuni.pathfinder.model.Level;
import bg.softuni.pathfinder.validation.annotation.UniqueEmail;
import bg.softuni.pathfinder.validation.annotation.UniqueUsername;
import bg.softuni.pathfinder.validation.annotation.ValidatePasswords;
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
@ValidatePasswords
public class UserRegisterDTO {
    @NotBlank
    @Size(min = 2, max = 200)
    @UniqueUsername
    private String username;

    @NotEmpty
    @Size(min = 5, max = 200)
    private String fullName;

    @Email(regexp = ".*@.*")
    @UniqueEmail
    private String email;

    @Min(1)
    @Max(90)
    private Integer age;

    @Size(min = 5, max = 100)
    private String password;

    private String confirmPassword;

    private Level level;
}
