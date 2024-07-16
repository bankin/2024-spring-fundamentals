package bg.softuni.pathfinder.web.dto;

import bg.softuni.pathfinder.model.Level;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RouteCategoryDTO {
    private Long id;
    private String name;
    private String description;
    private String imageUrl;
}
