package kfu.itis.chuprakov.service;

import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.UUID;

public class FileUploadService {
    public static final String UPLOAD_DIR = "img";


    public static final String FILE_PREFIX = System.getProperty("user.dir") +
            File.separator + "src" + File.separator + "main" + File.separator +
            "webapp" + File.separator + UPLOAD_DIR;

    public String uploadFile(Part part) throws IOException {
        if (part == null || part.getSize() == 0 || part.getSubmittedFileName() == null ||
                part.getSubmittedFileName().isEmpty()) {
            return null;
        }

        String originalFilename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
        String fileExtension = "";
        int dotIndex = originalFilename.lastIndexOf(".");
        if (dotIndex > 0) {
            fileExtension = originalFilename.substring(dotIndex);
        }
        String uniqueFilename = UUID.randomUUID().toString() + fileExtension;

        File uploadDir = new File(FILE_PREFIX);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        File file = new File(uploadDir, uniqueFilename);

        try (InputStream content = part.getInputStream();
             FileOutputStream fos = new FileOutputStream(file)) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = content.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
        }


        return UPLOAD_DIR + "/" + uniqueFilename;
    }
}