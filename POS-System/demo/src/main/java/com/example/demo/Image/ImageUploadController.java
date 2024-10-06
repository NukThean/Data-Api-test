package com.example.demo.Image;

import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Path;
import java.net.MalformedURLException;
import java.nio.file.Files;


@RestController
@RequestMapping(path = "api/pos/imageUpload")
public class ImageUploadController {

  @Autowired
  private ImageUploadService imageUploadService;

  @PostMapping
  public String uploadImage(@RequestParam("file") MultipartFile file,
      @RequestParam("productCode") String productCode) {
    try {
      return imageUploadService.uploadImage(file, productCode);
    } catch (IOException e) {
      e.printStackTrace();
      return "File upload failed!";
    }
  }

  @GetMapping("/view/{fileName}")
  public ResponseEntity<Resource> viewImage(@PathVariable String fileName) {
    try {
      Path imagePath = imageUploadService.getImagePath(fileName);
      Resource resource = new UrlResource(imagePath.toUri());

      if (resource.exists() || resource.isReadable()) {
        // Determine the content type based on the file extension
        String contentType = Files.probeContentType(imagePath);
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
            .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + fileName + "\"")
            .body(resource);
      } else {
        return ResponseEntity.notFound().build();
      }
    } catch (MalformedURLException e) {
      e.printStackTrace();
      return ResponseEntity.badRequest().build();
    } catch (IOException e) {
      e.printStackTrace();
      return ResponseEntity.internalServerError().build();
    }
  }
}
