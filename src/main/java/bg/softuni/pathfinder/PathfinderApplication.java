package bg.softuni.pathfinder;

import bg.softuni.pathfinder.config.cloudinary.CloudinaryConfigProperties;
import bg.softuni.pathfinder.model.UserRoles;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(CloudinaryConfigProperties.class)
public class PathfinderApplication {

    public static void main(String[] args) {
        SpringApplication.run(PathfinderApplication.class, args);
    }

}
