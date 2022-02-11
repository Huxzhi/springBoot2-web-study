package com.atguigu.admin.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 文件上传测试
 */
@Slf4j
@Controller
public class FormTestController {
    @GetMapping("/form_layouts")
    public String forem_layouts() {
        return "form/form_layouts";
    }

    /**
     * MultipartFile 自动封装上传过来的文件
     *
     * @param email
     * @param username
     * @param headerImg
     * @param photos
     * @return
     */
    @PostMapping("/upload")
    public String upload(@RequestParam("email") String email,//从请求参数中获取值
                         @RequestParam("username") String username,
                         @RequestParam("headerImg") MultipartFile headerImg,
                         @RequestParam("photos") MultipartFile[] photos) throws IOException {
        if (!headerImg.isEmpty()) {
            //保存到文件服务器，OSS服务器，然后异步更新
            String originalFilename = headerImg.getOriginalFilename();
            //可以再拼接一个UUID防止文件名重复
            headerImg.transferTo(new File("D:\\cache\\" + originalFilename));
        }
        if (photos.length > 0) {
            for (MultipartFile photo : photos) {
                if (!headerImg.isEmpty()) {
                    String originalFilename = headerImg.getOriginalFilename();
                    headerImg.transferTo(new File("D:\\cache\\" + originalFilename));
                }
            }
        }
        
        return "main";
    }
}
