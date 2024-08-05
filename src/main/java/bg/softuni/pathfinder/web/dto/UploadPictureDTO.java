package bg.softuni.pathfinder.web.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class UploadPictureDTO {
    private Long routeId;
    private String title;
    private MultipartFile picture;
}
