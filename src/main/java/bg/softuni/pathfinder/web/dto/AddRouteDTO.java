package bg.softuni.pathfinder.web.dto;

import bg.softuni.pathfinder.model.Level;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.File;

@Getter
@Setter
@NoArgsConstructor
public class AddRouteDTO {
    private String name;
    private String description;
    private Level level;
    private String videoUrl;
}
