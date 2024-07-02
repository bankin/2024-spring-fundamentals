package bg.softuni.pathfinder.service.dto;

import bg.softuni.pathfinder.model.Level;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfileDto {
    private String username;
    private String fullName;
    private int age;
    private Level level;
}
