package com.rest.api.service;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class Client_PhotoUploadService {

    public void uploadPhoto(MultipartFile photo, String clientId) throws IOException {
        photo.transferTo(new File(System.getProperty("user.home") + "/athletiqueimages/" + clientId + ".jpg"));
    }

}
