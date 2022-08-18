package com.atguigu.ggkt.vod.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author wzj
 * @Date 2022/8/16 21:30
 */
public interface FileService {

    //文件上传
    String upload(MultipartFile file);
}
