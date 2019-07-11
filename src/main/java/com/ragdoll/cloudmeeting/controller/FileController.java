package com.ragdoll.cloudmeeting.controller;

import com.ragdoll.cloudmeeting.dao.Meeting;
import com.ragdoll.cloudmeeting.dao.MeetingFile;
import com.ragdoll.cloudmeeting.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@ComponentScan(basePackages = "com.ragdoll.cloudmeeting.service")
public class FileController {

    @Autowired
    private FileService fileService;

    //会议资料上传
    @PostMapping("/meetingFile/{id}")
    @ResponseBody
    public String uploadMeeting(@PathVariable String id, @RequestParam("file") MultipartFile file){
        String path = "D:/uploadFile/Meeting/meeting" + id + "/";
        String msg = fileService.saveMeetingFile(file,path);
        if(msg == "未检测到文件" || msg == "上传成功"){
            return msg;
        }else{
            return "上传失败";
        }
    }

    //头像上传
    @PostMapping("/profile/{name}")
    @ResponseBody
    public String uploadHeadPicture(@PathVariable String name,@RequestParam("file") MultipartFile file){
        String msg = fileService.saveHeadPicture(file,name);
        if(msg == "未检测到文件") return msg;
        else if(msg == "文件不是图片") return "请上传一张jpg图片";
        else if(msg == "图片保存成功") return "上传成功";
        else return "上传失败";
    }

    //下载资料(返回文件名及url)
    @RequestMapping("/download/{conid}")
    @ResponseBody
    public MeetingFile[] download(@PathVariable String conid){
        String path = "D:/uploadfile/Meeting/meeting" + conid;
        //String[] fileList = fileService.readFile(path);
        if(fileService.readFile(path) != null){
            int len = fileService.readFile(path).length;
            MeetingFile[] meetingFiles = new MeetingFile[len];
            String[] fileList = fileService.readFile(path);
            for(int i = 0;i<len;i++){
                String name = fileList[i];
                String url = "http://42.159.11.72:8090/meetingFile/meeting" + conid + "/" + name;
                MeetingFile meetingFile = new MeetingFile(name,url);
                meetingFiles[i] = meetingFile;
                //fileList[i] = "http://42.159.11.72:8090/meetingFile/meeting" + conid + "/" + name;
            }
            return meetingFiles;
        }else{
            return null;
        }
    }

//    //查询会议所有的资料文件名
//    @RequestMapping("/getAllFileName/{conid}")
//    @ResponseBody
//    public String[] getAllFileName(@PathVariable Integer conid){
//        String path = "D:/uploadfile/Meeting/meeting" + conid;
//        return fileService.readFile(path);
//    }
}



