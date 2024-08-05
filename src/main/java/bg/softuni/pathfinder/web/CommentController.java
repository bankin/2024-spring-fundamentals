package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.service.CommentService;
import bg.softuni.pathfinder.web.dto.CreateCommentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("comments/create")
    public ModelAndView create(CreateCommentDTO createCommentDTO) {
        commentService.create(createCommentDTO);

        return new ModelAndView("redirect:/route/" + createCommentDTO.getRouteId());
    }

    @PostMapping("comments/delete/{routeId}/{id}")
    public ModelAndView delete(@PathVariable Long routeId, @PathVariable Long id) {
        commentService.delete(id);

        return new ModelAndView("redirect:/route/" + routeId);
    }
}
