package com.example.demo.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Constant {

  // Dynamically read from application.properties
  public static String PHOTO_DIRECTORY;

  @Value("${file.upload-dir}")
  public void setPhotoDirectory(String photoDirectory) {
    PHOTO_DIRECTORY = photoDirectory;
  }

  public static final String X_REQUESTED_WITH = "X-Requested-With";
}
