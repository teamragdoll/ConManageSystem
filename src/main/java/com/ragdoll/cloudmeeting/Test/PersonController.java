package com.ragdoll.cloudmeeting.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class PersonController {

    @Autowired
    private PersonDao personDao;

    @Autowired
    private PeopleDao peopleDao;

    @RequestMapping("/test")
    @ResponseBody
    public String test(@RequestBody Map<String,String> map){
        Person p = new Person();
        p.setName(map.get("name"));
        personDao.save(p);
        return "添加成功";
    }
    @GetMapping("/test2/{id}")
    @ResponseBody
    public String test2(@PathVariable("id") Integer i){
        personDao.deleteById(i);
        return "删除成功";
    }

    @RequestMapping("/test3")
    @ResponseBody
    public String test3(@RequestBody Map<String,String>[] persons){
        int n = persons.length;
        String msg = "";
        for(int i = 0;i<n;i++){
            msg = msg+persons[i].get("name");
        }
        return msg;
    }

    @PostMapping("/file/upload")
    @ResponseBody
    public String test4(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "please select a file to upload");
            return "result";
        }

        String filenmae = file.getOriginalFilename();
        String path = "D:/uploadfile/upload/"+filenmae; //request.getServletContext().getRealPath("/upload/" + filenmae);
        File savefile = new File(path);
            //判断上传文件的保存目录是否存在
//            if (!savefile.exists() && !savefile.isDirectory()) {
//                System.out.println(path + "目录不存在，需要创建");
//                savefile.mkdir();
//            }
        if(!savefile.getParentFile().exists()){
            savefile.getParentFile().mkdir();
        }

        try {
            file.transferTo(savefile);
        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return "message";
    }

    @RequestMapping("/test5")
    @ResponseBody
    public String test5(@RequestBody People people){
        peopleDao.save(people);
        return "存储成功";
    }

    @RequestMapping("/test6")
    @ResponseBody
    public String test6(@RequestBody List<String> strs){
        return strs.get(0);
    }
}
