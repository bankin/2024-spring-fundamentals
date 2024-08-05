package bg.softuni.pathfinder.config.cloudinary;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "cloudinary")
@Getter
@Setter
public class CloudinaryConfigProperties {

    private String cloudName;
    private String apiKey;
    private String apiSecret;
}
