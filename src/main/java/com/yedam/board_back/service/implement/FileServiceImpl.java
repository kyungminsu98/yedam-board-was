package com.yedam.board_back.service.implement;

import java.io.File;
import java.util.UUID; // 올바른 UUID 클래스 import

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.yedam.board_back.service.FileService;

@Service
public class FileServiceImpl implements FileService {
    @Value("${file.path}")
    private String filePath;

    @Value("${file.url}")
    private String fileUrl;

    @Override
    public String upload(MultipartFile file) {
        if (file.isEmpty()) return null;

        String originalFileName = file.getOriginalFilename();
        if (originalFileName == null || originalFileName.isEmpty()) {
            return null;  // Original file name is required
        }
        
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String uuid = UUID.randomUUID().toString();
        String saveFileName = uuid + extension;
        String savePath = filePath + saveFileName;

        try {
            // 파일 저장
            file.transferTo(new File(savePath));
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }

        return fileUrl + saveFileName;
    }

    @Override
    public Resource getImage(String fileName) {
        try {
            return new UrlResource("file:" + filePath + fileName);
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }
}
