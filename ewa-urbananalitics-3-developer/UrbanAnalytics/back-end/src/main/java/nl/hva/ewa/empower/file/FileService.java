package nl.hva.ewa.empower.file;

import nl.hva.ewa.empower.group.Group;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class FileService {
    public void storeGroupImage(MultipartFile file, Group group) throws IOException {
        Path filePath = Paths.get("./src/main/resources/files/group/").toRealPath().normalize().resolve("" + group.getGroupId());
        System.out.println(filePath.toString());

        Files.createDirectories(filePath);

        Path targetLoc = filePath.resolve("groupImage.jpg");
        Files.copy(file.getInputStream(), targetLoc, StandardCopyOption.REPLACE_EXISTING);
    }
}
