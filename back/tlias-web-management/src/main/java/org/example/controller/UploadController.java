package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.pojo.Result;
import org.example.utils.AliyunOSSOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {
    /**
     * 本地磁盘存储方案
     */
   /*
    @PostMapping("/upload")             // spring 提供的接收文件类的数据类型
    public Result uploda(String name, Integer age, MultipartFile file) throws Exception {
        log.info("接收参数： {}， {}， {}", name, age, file);

        //获取原始文件名
       // String originalFilename = file.getOriginalFilename();   1.jpg 2.jpg

        //保存文件
       // file.transferTo(new File("F:\\IDEA.JAVA\\WEB后端基础\\文件上传\\images"+ originalFilename));


        // 但是这样的话，保存的文件就会有一个弊端，那就是如果新保存的文件和之前保存的文件，命名有重复的话，新的文件就会把旧得的文件给覆盖掉
        // 所以为了避免这个命名上的重复，于是我们就需要提供一个方法供我们取名字
        //而IDEA为了解决这种情况，就提供了一种方法，叫做 “UUID”

        //获取原始文件名
        String originalFilename = file.getOriginalFilename();

        //新的文件名
        // 1. 获取后缀                      // 从最后一个 "." 索引开始到最后   也就是后缀
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        // 2. 新的文件名
        String newFileName = UUID.randomUUID().toString() + extension;


        //保存文件
        file.transferTo(new File("F:\\IDEA.JAVA\\WEB后端基础\\Tlias系统\\images\\"+ newFileName));

        // 但上传的文件的大小并不是无限制的，默认单次文件上传最大为1MB， 最大请求大小为10MB，
        // 可以在配置yml中修改

        return Result.success();
        }
      */

    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception {
        log.info("文件上传：{}", file.getOriginalFilename());
        // 将文件交给OSS存储管理
       String url =  aliyunOSSOperator.upload(file.getBytes(),file.getOriginalFilename());
       log.info("文件上传OSS，url : {}", url);

       return Result.success(url);
    }

}
