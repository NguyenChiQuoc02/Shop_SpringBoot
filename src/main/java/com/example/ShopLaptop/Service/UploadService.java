package com.example.ShopLaptop.Service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    public String UploadFile(MultipartFile file, String targetFolder);
}
