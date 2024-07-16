package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.model.CategoryType;
import bg.softuni.pathfinder.model.Level;
import bg.softuni.pathfinder.service.RouteService;
import bg.softuni.pathfinder.service.dto.RouteShortInfoDTO;
import bg.softuni.pathfinder.web.dto.AddRouteDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

/**
 * Controller to handle all things route relates
 */
@Controller
@RequiredArgsConstructor
public class RouteController {
    private final RouteService routeService;

    /**
     * Method to handle the listing of all routes.
     *
     * @param model the Model for the view
     * @return the list routes view
     */
    @GetMapping("/routes")
    public String routes(Model model) {
        List<RouteShortInfoDTO> routes = routeService.getAll();

        model.addAttribute("allRoutes", routes);

        return "routes";
    }

    @GetMapping("add-route")
    public ModelAndView addRoute() {
        ModelAndView modelAndView = new ModelAndView("add-route");

        modelAndView.addObject("route", new RouteShortInfoDTO());
        modelAndView.addObject("levels", Level.values());
        modelAndView.addObject("categoryTypes", CategoryType.values());

        return modelAndView;
    }


    @ModelAttribute("routeData")
    public AddRouteDTO routeData() {
        return new AddRouteDTO();
    }

    @PostMapping("/add-route")
    public String doAddRoute(
            @Valid AddRouteDTO data,
            @RequestParam("gpxCoordinates") MultipartFile file,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) throws IOException {
        // if (!valid) return errors

        routeService.add(data, file);

        return "redirect:/add-route";
    }

    @GetMapping("route/{id}")
    public ModelAndView addRoute(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("route-details");

        modelAndView.addObject("route", routeService.getDetails(id));

        return modelAndView;
    }

    @GetMapping("/routes/{category}")
    public ModelAndView getRoutesByCategory(@PathVariable CategoryType category) {
        String view = "";
        switch (category){
            case CAR -> view ="car";
            case BICYCLE -> view ="bicycle";
            case PEDESTRIAN -> view ="pedestrian";
            case MOTORCYCLE -> view ="motorcycle";
        }

        ModelAndView modelAndView = new ModelAndView(view);

        modelAndView.addObject("routes", routeService.getRouteByCategory(category));

        return modelAndView;
    }
}
