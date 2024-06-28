package com.example.ShopLaptop.Service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
    public String UploadAvatar(MultipartFile file, String targetFolder);
}
