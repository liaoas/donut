package com.liao.service.impl;

import com.liao.exception.FileException;
import com.liao.util.FileProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileService {

    private final Path fileStorageLocation;

    @Autowired
    public FileService(FileProperties fileProperties) {
        this.fileStorageLocation = Paths.get(fileProperties.getUploadDir()).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            try {
                throw new FileException("无法创建将存储上传文件的目录。", ex);
            } catch (FileException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 存储文件到系统
     *
     * @param file 文件
     * @return 文件名
     */
    public String storeFile(MultipartFile file) {
        // 格式化文件名称
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        System.out.println("我是FileService工厂类格式化后的文件名称：" + fileName);
        try {
            // 检查是否包含无效字符
            if (fileName.contains("..")) {
                try {
                    throw new FileException("抱歉! 文件名包含无效的路径序列 " + fileName);
                } catch (FileException e) {
                    e.printStackTrace();
                }
            }

            // 将文件复制到目标位置（替换具有相同名称的现有文件）
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            try {
                throw new FileException("无法存储文件 " + fileName + "。 请再试一遍！", ex);
            } catch (FileException e) {
                e.printStackTrace();
            }
        }
        return fileName;
    }

    /**
     * 加载文件
     *
     * @param fileName 文件名
     * @return 文件
     */
    public Resource loadFileAsResource(String fileName) {
        Resource resource = null;
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {

            } else {
                try {
                    throw new FileException("文件未找到 " + fileName);
                } catch (FileException e) {
                    e.printStackTrace();
                }
            }
        } catch (MalformedURLException ex) {
            try {
                throw new FileException("文件未找到 " + fileName, ex);
            } catch (FileException e) {
                e.printStackTrace();
            }
        }
        return resource;
    }

}
