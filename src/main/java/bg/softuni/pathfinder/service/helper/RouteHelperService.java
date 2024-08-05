package bg.softuni.pathfinder.service.helper;

import bg.softuni.pathfinder.data.RouteRepository;
import bg.softuni.pathfinder.exception.RouteNotFoundException;
import bg.softuni.pathfinder.model.Route;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RouteHelperService {

    private final RouteRepository routeRepository;

    public Route getByIdOrThrow(Long id){
        return routeRepository.findById(id)
                .orElseThrow(() -> new RouteNotFoundException("No route found with id " + id));
    }
}
