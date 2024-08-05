package bg.softuni.pathfinder.init;


import bg.softuni.pathfinder.constant.AppConstant;
import bg.softuni.pathfinder.data.RouteRepository;
import bg.softuni.pathfinder.model.Route;
import bg.softuni.pathfinder.util.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TransferGpxCoordinatesToFile implements CommandLineRunner {

    @Value("${gpx-coordinates.migrate:false}")
    private Boolean shouldMigrate;
    private final RouteRepository routeRepository;

    @Override
    public void run(String... args) throws Exception {
        if (shouldMigrate) {
            List<Route> routes = routeRepository.findAll().stream()
                    .filter(route -> route.getGpxCoordinates() != null && route.getGpxCoordinates().startsWith("<?xml"))
                    .toList();

            routes.forEach(route -> {
                String path = getFilePath(route.getName(), route.getAuthor().getUsername());
                FileService.writeFile(AppConstant.GPX_PATH + path, route.getGpxCoordinates().getBytes());

                route.setGpxCoordinates(path);
                routeRepository.save(route);
            });
        }
    }

    private String getFilePath(String routeName, String username) {
        String pathPattern = "%s\\%s_%s.xml";
        return String.format(pathPattern,
                username,
                transformRouteName(routeName),
                UUID.randomUUID());
    }

    private String transformRouteName(String routeName) {
        return routeName.toLowerCase()
                .replaceAll("\\s+", "_")
                .replaceAll("\"", "");
    }
}
