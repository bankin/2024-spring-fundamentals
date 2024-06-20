package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.model.CategoryType;
import bg.softuni.pathfinder.model.Level;
import bg.softuni.pathfinder.service.RouteService;
import bg.softuni.pathfinder.service.dto.RouteShortInfoDTO;
import bg.softuni.pathfinder.web.dto.AddRouteDTO;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

/**
 * Controller to handle all things route relates
 */
@Controller
public class RouteController {
    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

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
}
