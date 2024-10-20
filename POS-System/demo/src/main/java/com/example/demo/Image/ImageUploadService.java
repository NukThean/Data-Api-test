package com.example.demo.Image;

import org.springframework.beans.factory.annotation.Value;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;

@Service
public class ImageUploadService {

  // Inject the upload directory from the properties file
  @Value("${file.upload-dir}")
  private String uploadDir;

  // Method to handle the file upload
  public String uploadImage(MultipartFile file, String productCode) throws IOException {
    if (file.isEmpty()) {
      return "Please select a file to upload.";
    }

    // Get the original file extension
    String fileExtension = "";
    String originalFileName = file.getOriginalFilename();

    if (originalFileName != null && originalFileName.contains(".")) {
      fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
    }

    // Use the productCode as the file name and keep the extension
    String newFileName = productCode + "." + fileExtension;

    // Define the path to save the file
    Path path = Paths.get(uploadDir + File.separator + newFileName);

    // Ensure the directory exists
    Files.createDirectories(path.getParent());

    // Save the file to the target location
    Files.copy(file.getInputStream(), path);

    return "File uploaded successfully: " + newFileName;
  }

  // New method to retrieve the image file as a Path
  public Path getImagePath(String fileName) {
    return Paths.get(uploadDir + File.separator + fileName).normalize();
  }


}
