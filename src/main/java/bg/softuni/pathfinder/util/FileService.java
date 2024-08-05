package bg.softuni.pathfinder.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FileService {

    public static void writeFile(String fullPath, byte[] content) {
        try {
            File newFile = new File(fullPath);
            newFile.getParentFile().mkdirs();
            newFile.createNewFile();

            OutputStream outputStream = new FileOutputStream(newFile);
            outputStream.write(content);
        } catch (IOException e) {
            e.setStackTrace(e.getStackTrace());
        }
    }

    public static void writeFile(String fullPath, MultipartFile file) {
        try {
            File newFile = new File(fullPath);
            newFile.getParentFile().mkdirs();
            newFile.createNewFile();

            OutputStream outputStream = new FileOutputStream(newFile);
            outputStream.write(file.getBytes());
        } catch (IOException e) {
            e.setStackTrace(e.getStackTrace());
        }
    }
}
