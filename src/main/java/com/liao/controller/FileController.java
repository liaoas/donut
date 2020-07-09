package com.liao.controller;
import com.liao.config.ControllerLog;
import com.liao.service.impl.FileService;
import com.liao.util.UploadFileResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
@Controller
@RequestMapping("/file")
public class FileController {
    /**
     * FileController类在接收到用户的请求后，使用FileService类提供的storeFile()方法将文件写入到系统中进行存储，
     * 其存储目录就是在application.properties配置文件中的file.upload-dir参数的值之前./uploads。
     * 下载接口downloadFile()在接收到用户请求之后，使用FileService类提供的loadFileAsResource()方法获取存储在系统中文件并返回文件供用户下载。
     *
     */
    private static final Logger logger = (Logger) LoggerFactory.getLogger(FileController.class);

    private final FileService fileService;

    /**
     * set注入
     * @param fileService
     */
    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    /**
     * 上传文件
     * @param file
     * @return
     */
    @PostMapping("/uploadFile")
    @ResponseBody
    @ControllerLog(description = "文件上传接口")
    public UploadFileResponse uploadFile(@RequestParam("file")MultipartFile file){
        // 调用FileService 方法 将文件上传到系统
        String finleName = fileService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                                .path("/downloadFile/")
                                .path(finleName)
                                .toUriString();
        return new UploadFileResponse(finleName,fileDownloadUri,file.getContentType(),file.getSize());
    }

    /**
     * 上传多个文件
     * @param files
     * @return
     */
    @PostMapping("/uploadMultipleFiles")
    @ResponseBody
    @ControllerLog(description = "多文件上传接口")
    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files")MultipartFile[]files){
        return Arrays.stream(files)
                .map(this::uploadFile)
                .collect(Collectors.toList());
    }

    /**
     * 文件下载
     * @param fileName
     * @param request
     * @return
     */
    @PostMapping("/downloadFile/{fileName:.+}")
    @ResponseBody
    @ControllerLog(description = "文件下载接口")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request){
        // 将文件作为资源加载
        Resource resource = fileService.loadFileAsResource(fileName);

        //尝试确定文件的内容类型
        String contentType = null;

        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        }catch (IOException ex){
            logger.info("无法确定文件类型。");
        }

        //如果无法确定类型，则退回到默认内容类型

        if (contentType == null){
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + resource.getFilename()+"\"")
                .body(resource);
    }
}
