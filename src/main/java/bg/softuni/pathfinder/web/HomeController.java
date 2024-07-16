package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Random;

/**
 * Controller handling the info pages - home, about, contact
 */
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final RouteService routeService;

    @GetMapping("/")
    public String index(Model model) {
        double sofiaTemp = new Random().nextDouble();

        model.addAttribute("sofiaTemperature", sofiaTemp);
        model.addAttribute("commentedRoute", routeService.getMostCommentedRoute());

        return "index";
    }

    @GetMapping("/about")
    public ModelAndView index() {
        return new ModelAndView("about");
    }

    @GetMapping("/access-denied")
    public ModelAndView accessDenied() {
        return new ModelAndView("about");
    }
}
