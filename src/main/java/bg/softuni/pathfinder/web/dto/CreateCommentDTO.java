package bg.softuni.pathfinder.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCommentDTO {
    private Long routeId;
    private String message;
}
