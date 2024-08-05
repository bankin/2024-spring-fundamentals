package bg.softuni.pathfinder.service;

import bg.softuni.pathfinder.constant.AppConstant;
import bg.softuni.pathfinder.data.PictureRepository;
import bg.softuni.pathfinder.data.RouteRepository;
import bg.softuni.pathfinder.exception.RouteNotFoundException;
import bg.softuni.pathfinder.model.CategoryType;
import bg.softuni.pathfinder.model.Picture;
import bg.softuni.pathfinder.model.Route;
import bg.softuni.pathfinder.service.dto.RouteDetailsCommentDTO;
import bg.softuni.pathfinder.service.dto.RouteDetailsDTO;
import bg.softuni.pathfinder.service.dto.RouteShortInfoDTO;
import bg.softuni.pathfinder.service.helper.RouteHelperService;
import bg.softuni.pathfinder.util.YoutubeLinkConverter;
import bg.softuni.pathfinder.web.dto.AddRouteDTO;
import bg.softuni.pathfinder.web.dto.RouteCategoryDTO;
import bg.softuni.pathfinder.web.dto.UploadPictureDTO;
import io.jenetics.jpx.GPX;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

@Service
@RequiredArgsConstructor
public class RouteService {
    private final RouteRepository routeRepository;
    private final PictureRepository pictureRepository;
    private final Random random = new Random();
    private final ModelMapper modelMapper;
    private final UserHelperService userHelperService;
    private final RouteHelperService routeHelperService;

    @Transactional
    public List<RouteShortInfoDTO> getAll() {
        return routeRepository.findAll()
                .stream()
                .map(this::mapToShortInfo)
                .toList();
    }

    @Transactional
    public RouteShortInfoDTO getRandomRoute() {
        long routeCount = routeRepository.count();
        long randomId = random.nextLong(routeCount) + 1;

        Optional<Route> route = routeRepository.findById(randomId);

        if (route.isEmpty()) {
            // throw exception; return empty
        }

        return mapToShortInfo(route.get());
    }

    private RouteShortInfoDTO mapToShortInfo(Route route) {
        RouteShortInfoDTO dto = modelMapper.map(route, RouteShortInfoDTO.class);

        Optional<Picture> first = route.getPictures().stream().findFirst();
        first.ifPresent(picture -> dto.setImageUrl(picture.getUrl()));

        return dto;
    }

    public boolean add(AddRouteDTO data, MultipartFile gpxFile) throws IOException {
        Route toInsert = modelMapper.map(data, Route.class);
        toInsert.setVideoUrl(YoutubeLinkConverter.convert(data.getVideoUrl()));
        toInsert.setAuthor(userHelperService.getUser());

        routeRepository.save(toInsert);
//        Path destinationFile = Paths
//                .get("src", "main", "resources", "uploads", "file.gpx")
//                .normalize()
//                .toAbsolutePath();
//
//        try (InputStream inputStream = gpxFile.getInputStream()) {
//            Files.copy(inputStream, destinationFile,
//                    StandardCopyOption.REPLACE_EXISTING);
//        }


        // originalFilename, fileLocation ->  /uploads/{userId}/{fileId}.{ext}

        return false;
    }

    @Transactional(readOnly = true)
    public RouteDetailsDTO getDetails(Long id) {
        Route route = routeRepository.findById(id)
                .orElseThrow(() -> new RouteNotFoundException("Route with id: " + id + " was not found"));

        RouteDetailsDTO dto = modelMapper.map(route, RouteDetailsDTO.class);
        dto.setVideoUrl("https://www.youtube.com/embed/" + dto.getVideoUrl());
        dto.setImageUrls(List.of("/images/pic4.jpg", "/images/pic1.jpg"));
        dto.setComments(route.getComments().stream()
                .map(comment -> modelMapper.map(comment, RouteDetailsCommentDTO.class))
                .toList());

        return dto;
    }

    @Transactional(readOnly = true)
    public List<RouteCategoryDTO> getRouteByCategory(CategoryType category) {
        List<Route> allByCategoryName = routeRepository.findAllByCategories_Name(category);

        return allByCategoryName.stream()
                .map(route -> {
                    RouteCategoryDTO dto = modelMapper.map(route, RouteCategoryDTO.class);
                    dto.setImageUrl(pictureRepository.findFirstByRoute_Id(dto.getId()).getUrl());

                    return dto;
                })
                .toList();
    }

    @Transactional(readOnly = true)
    public RouteCategoryDTO getMostCommentedRoute() {
        Route mostCommentedRoute = routeRepository.findAll().stream()
                .max(Comparator.comparingInt(route -> route.getComments().size()))
                .orElse(null);

        RouteCategoryDTO routeDto = modelMapper.map(mostCommentedRoute, RouteCategoryDTO.class);
        routeDto.setImageUrl("http://res.cloudinary.com/ch-cloud/image/upload/v1630581072/d47iy8kxv6qni8euhojk.jpg");

        return routeDto;
    }


    public List<List<Double>> getCoordinates(Long routeId) {
        Route route = routeHelperService.getByIdOrThrow(routeId);

        try {
            GPX gpx = GPX.read(Path.of(AppConstant.GPX_PATH + route.getGpxCoordinates()));

            return gpx.getTracks().get(0).getSegments().get(0).getPoints().stream()
                    .map(point -> {
                        List<Double> coordinates = new ArrayList<>();
                        coordinates.add(point.getLongitude().doubleValue());
                        coordinates.add(point.getLatitude().doubleValue());
                        return coordinates;
                    })
                    .toList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }

    }
}
