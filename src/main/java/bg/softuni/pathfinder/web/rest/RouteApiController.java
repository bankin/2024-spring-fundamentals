package bg.softuni.pathfinder.web.rest;

import bg.softuni.pathfinder.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RouteApiController {

    private final RouteService routeService;

    @GetMapping("api/routes/coordinates/{routeId}")
    public List<List<Double>> getCoordinates(@PathVariable Long routeId) {
        return routeService.getCoordinates(routeId);
    }
}
