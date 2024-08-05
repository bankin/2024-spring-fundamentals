package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.service.PictureService;
import bg.softuni.pathfinder.web.dto.UploadPictureDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class PictureController {
    private final PictureService pictureService;

    @PostMapping("/picture/upload-picture")
    public ModelAndView uploadPicture(UploadPictureDTO uploadPictureDTO) {
        pictureService.create(uploadPictureDTO);

        return new ModelAndView("redirect:/route/" + uploadPictureDTO.getRouteId());
    }
}
