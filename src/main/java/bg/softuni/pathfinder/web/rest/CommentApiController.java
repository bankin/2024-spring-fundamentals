package bg.softuni.pathfinder.web.rest;

import bg.softuni.pathfinder.service.CommentService;
import bg.softuni.pathfinder.service.dto.CreateCommentApiDTO;
import bg.softuni.pathfinder.web.dto.CreateCommentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentApiController {

    private final CommentService commentService;

    @PostMapping("/api/comments")
    public CreateCommentApiDTO create(@RequestBody CreateCommentDTO createCommentApiDTO){
        return commentService.createApi(createCommentApiDTO);
    }
}
