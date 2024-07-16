package bg.softuni.pathfinder.service;

import bg.softuni.pathfinder.data.RouteRepository;
import bg.softuni.pathfinder.exception.RouteNotFoundException;
import bg.softuni.pathfinder.model.CategoryType;
import bg.softuni.pathfinder.model.Picture;
import bg.softuni.pathfinder.model.Route;
import bg.softuni.pathfinder.service.dto.RouteDetailsDTO;
import bg.softuni.pathfinder.service.dto.RouteShortInfoDTO;
import bg.softuni.pathfinder.web.dto.AddRouteDTO;
import bg.softuni.pathfinder.web.dto.RouteCategoryDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class RouteService {
    private final RouteRepository routeRepository;
    private final Random random = new Random();
    private final ModelMapper modelMapper;

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
        dto.setImageUrl(first.get().getUrl());

        return dto;
    }

    public boolean add(AddRouteDTO data, MultipartFile gpxFile) throws IOException {
        Route toInsert = modelMapper.map(data, Route.class);

        Path destinationFile = Paths
                .get("src", "main", "resources", "uploads", "file.gpx")
                .normalize()
                .toAbsolutePath();

        try (InputStream inputStream = gpxFile.getInputStream()) {
            Files.copy(inputStream, destinationFile,
                    StandardCopyOption.REPLACE_EXISTING);
        }


        // originalFilename, fileLocation ->  /uploads/{userId}/{fileId}.{ext}

        return false;
    }

    public RouteDetailsDTO getDetails(Long id) {
        Route route = routeRepository.findById(id)
                .orElseThrow(() -> new RouteNotFoundException("Route with id: " + id + " was not found"));

        RouteDetailsDTO dto = modelMapper.map(route, RouteDetailsDTO.class);
        dto.setVideoUrl("https://www.youtube.com/embed/" + dto.getVideoUrl());
        dto.setImageUrls(List.of("/images/pic4.jpg", "/images/pic1.jpg"));

        return dto;
    }

    public List<RouteCategoryDTO> getRouteByCategory(CategoryType category) {
        List<Route> allByCategoryName = routeRepository.findAllByCategories_Name(category);

        return allByCategoryName.stream()
                .map(route -> modelMapper.map(route, RouteCategoryDTO.class))
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
}
