package bg.softuni.pathfinder.service.dto;

import bg.softuni.pathfinder.model.Level;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RouteDetailsDTO {
    private long id;
    private String name;
    private String description;
    private Level level;
    private String videoUrl;
    private String authorName;
    private List<String> imageUrls;
}
