package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.model.Level;
import bg.softuni.pathfinder.service.UserService;
import bg.softuni.pathfinder.web.dto.UserLoginDTO;
import bg.softuni.pathfinder.web.dto.UserRegisterDTO;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("users/register")
    public String viewRegister(Model model) {
        model.addAttribute("registerData", new UserRegisterDTO());
        model.addAttribute("levels", Level.values());

        return "register";
    }

    @PostMapping("users/register")
    public String doRegister(
            UserRegisterDTO data,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {

//        if (bindingResult.hasErrors()) {
//            redirectAttributes.addFlashAttribute("registerData", data);
////            redirectAttributes.addAttribute("org.springframework.validation.BindingResult.UserRegisterDTO", bindingResult);
//
//            // handle errors
//            return "register";
//        }

        userService.register(data);

        // register user
        return "redirect:/users/login";
    }

    @GetMapping("users/login")
    public ModelAndView viewLogin() {
        ModelAndView modelAndView = new ModelAndView("login");

        modelAndView.addObject("loginData", new UserLoginDTO());

        return modelAndView;
    }


    @PostMapping("users/login")
    public String login(UserLoginDTO loginData) {
        userService.login(loginData);

        return "redirect:/";
    }

    @PostMapping("users/logout")
    public String logout() {
        userService.logout();

        return "redirect:/";
    }

    @GetMapping("users/profile")
    public ModelAndView profile() {
        ModelAndView modelAndView = new ModelAndView("profile");

        modelAndView.addObject("profileData", userService.getProfileData());

        return modelAndView;
    }
}
