package bg.softuni.pathfinder.demo.demorest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @GetMapping(value = "/api/hello-world", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getString() {

        return "{ \"name\" : \"Hello World\" }";
    }
}
