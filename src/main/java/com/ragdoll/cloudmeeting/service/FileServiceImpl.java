package com.ragdoll.cloudmeeting.service;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class FileServiceImpl implements FileService {

    //会议资料存储
    @Override
    public String saveMeetingFile(MultipartFile file,String path){
        if (file.isEmpty()) {
           return "未检测到文件";
        }

        String filenmae = file.getOriginalFilename();
        String savepath = path + filenmae; //request.getServletContext().getRealPath("/upload/" + filenmae);
        File savefile = new File(savepath);

        if(!savefile.getParentFile().exists()){
            savefile.getParentFile().mkdir();
        }

        try{
            file.transferTo(savefile);
            return "上传成功";
        }catch(IOException e){
            System.out.println(e);
            return "系统存储失败";
        }
    }

    //头像存储
    @Override
    public String saveHeadPicture(MultipartFile file,String username){
       if(file.isEmpty()){
           return "未检测到文件";
       }

       String filename = file.getOriginalFilename();
       String type = filename.substring(filename.lastIndexOf("."));
       if(!(type.equals(".jpg"))){
           return "文件不是jpg图片";
       }

       String savePath = "D:/uploadfile/profile/" + username + type;
       File saveFile = new File(savePath);

        if(!saveFile.getParentFile().exists()){
            saveFile.getParentFile().mkdir();
        }

       try{
           file.transferTo(saveFile);
           return "图片保存成功";
       }catch (IOException e){
           System.out.println(e);
           return "图片保存失败";
       }

    }

    //文件查询
    @Override
    public String[] readFile(String path){
        try{
            File file = new File(path);
            if(file.exists()) {
                if (file.isDirectory()) {
                    String[] fileList = file.list();
                    return fileList;
                } else {
                    return null;
                }
            }else{
                return null;
            }
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    //设置默认头衔
    @Override
    public void setFirstProfile(String username){
        String path = "D:/uploadfile/默认.jpg";
        File firstProfile = new File(path);
        String savePath = "D:/uploadfile/profile/" + username + ".jpg";
        File saveFile = new File(savePath);
        if(!saveFile.getParentFile().exists()){
            saveFile.getParentFile().mkdir();
        }

        try{
            FileUtils.copyFile(firstProfile,saveFile);
        }catch(IOException e){
            System.out.println(e);
        }
    }
}
