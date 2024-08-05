package bg.softuni.pathfinder.service;

import com.cloudinary.Cloudinary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CloudinaryService {

    private final Cloudinary cloudinary;

    public String upload(MultipartFile file, String folder) {
        try {
            return upload(file.getBytes(), folder);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public String upload(byte[] file, String folder) {
//        Map<String, String> options = new HashMap<>();
//        options.put("folder", folder);

        try {
            Map data = cloudinary.uploader().upload(file, new HashMap<>());

            return (String) data.get("secure_url");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
