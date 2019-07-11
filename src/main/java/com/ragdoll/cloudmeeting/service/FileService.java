package com.ragdoll.cloudmeeting.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface FileService {
    //会议资料存储
    String saveMeetingFile(MultipartFile file,String path);

    //头像保存
    String saveHeadPicture(MultipartFile file,String username);

    //读取文件
    String[] readFile(String path);

    //设置默认头像
    void setFirstProfile(String username);
}
