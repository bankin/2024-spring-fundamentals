package bg.softuni.pathfinder.service;

import bg.softuni.pathfinder.data.PictureRepository;
import bg.softuni.pathfinder.model.Picture;
import bg.softuni.pathfinder.service.helper.RouteHelperService;
import bg.softuni.pathfinder.web.dto.UploadPictureDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PictureService {

    private final PictureRepository pictureRepository;
    private final CloudinaryService cloudinaryService;
    private final RouteHelperService routeHelperService;
    private final UserHelperService userHelperService;

    public void create(UploadPictureDTO uploadPictureDTO) {
        String path = cloudinaryService.upload(uploadPictureDTO.getPicture(), "");

        Picture picture = new Picture();
        picture.setUrl(path);
        picture.setTitle(uploadPictureDTO.getTitle());
        picture.setRoute(routeHelperService.getByIdOrThrow(uploadPictureDTO.getRouteId()));
        picture.setAuthor(userHelperService.getUser());

        pictureRepository.save(picture);
    }
}
