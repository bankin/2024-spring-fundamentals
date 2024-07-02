package bg.softuni.pathfinder.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RouteShortInfoDTO {
    private long id;
    private String name;
    private String description;
    private String imageUrl;
}
