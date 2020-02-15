package com.cute.community.util;


import com.cute.community.dto.PhotoUploadDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 *@ClassName UploadUtil
 *@Description 上传图片类
 *@Author Lenovo
 *@Date 2020/2/15
 *@Version 1.0
**/

@Slf4j
@Component
public class UploadUtil {
    @Value("${img.location}")
    private  String imageFilePath;
    @Value("${img.url}")
    private  String imageUrl;
    public PhotoUploadDTO fileUpload(MultipartFile file){
        log.info("开始上传图片");
        // 获取文件名
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        String newFileName = System.currentTimeMillis() + "." + suffix;
        log.info("上传文件文件名称：{}",newFileName);
        log.info("上传文件大小 ：{}",file.getSize());

        //文件存储地址：imageFilePath+newFileName
        log.info(imageFilePath + newFileName);
        try {

            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(imageFilePath + newFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        PhotoUploadDTO photoUploadDTO = new PhotoUploadDTO();
        photoUploadDTO.setFileName(fileName);
        photoUploadDTO.setSuffix(suffix);
        photoUploadDTO.setNewFileName(newFileName);
        photoUploadDTO.setPhotoUrl(imageUrl + "/image/" +newFileName);
        return photoUploadDTO;
    }
}
