//洪涛 2017302580282

package com.ragdoll.cloudmeeting.controller;

import com.ragdoll.cloudmeeting.dao.MUserinfo;
import com.ragdoll.cloudmeeting.dao.muser;
import com.ragdoll.cloudmeeting.service.FileService;
import com.ragdoll.cloudmeeting.service.MuserInfoService;
import com.ragdoll.cloudmeeting.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/user")
@Controller
@ComponentScan(basePackages = "com.ragdoll.cloudmeeting.service")
public class MuserInfoController {

    @Autowired
    private MuserInfoService muserInfoService;

    @Autowired
    private UserService userService;

    @Autowired
    private FileService fileService;


    //客户端注册
    @PostMapping("/register")
    @ResponseBody
    public String register(@RequestBody Map<String,String> map){

        MUserinfo muserInfo = new MUserinfo();

        muserInfo.setMname(map.get("mname"));
        muserInfo.setCompanyid(map.get("mcompanyid"));
        muserInfo.setMaddress(map.get("maddress"));
        muserInfo.setMemail(map.get("memail"));
        muserInfo.setMtel(map.get("mtel"));
        muserInfo.setMposition(map.get("mposition"));
        muserInfo.setMusername(map.get("musername"));

        String msg = muserInfoService.addUserInfo(muserInfo);
        if(msg == "注册成功"){
            muser user = new muser();
            user.setMpassword(map.get("mpassword"));
            user.setMusername(map.get("musername"));
            String msg2 = userService.addUser(user);
            fileService.setFirstProfile(muserInfo.getMusername());
            if(msg2 == "已存在" || msg2 == "添加失败"){
                return "注册失败,登录表存在";
            }else{
                return "注册成功";
            }
        } else if (msg == "邮箱已被注册") {
            return "邮箱已被注册";
        }else{
            return "注册失败";
        }
    }

    //根据用户名，查询用户详细信息
    @GetMapping("/getUserInfo/{name}")
    @ResponseBody
    public MUserinfo findByUsername(@PathVariable("name") String name){
        return muserInfoService.findByUsername(name);
    }

    //更改用户信息
    @PostMapping("/changeUserInfo/{name}")
    @ResponseBody
    public String changeUserinfo(@RequestBody Map<String,String> map,@PathVariable("name") String name){
        return muserInfoService.changeUserinfo(name,map);
    }

    //根据公司id查找公司里面所有人员
    @GetMapping("/findUserByCId/{cid}")
    @ResponseBody
    public List<MUserinfo> findUserByCId(@PathVariable("cid") String cid){
        List<MUserinfo> userinfos = muserInfoService.findUserInfoByCId(cid);
        return userinfos;
    }

    //根据用户的公司id查找该用户所在公司的所有人员信息
    @GetMapping("/findUserByUsername/{name}")
    @ResponseBody
    public List<MUserinfo> findUserByUsername(@PathVariable("name") String username){
        MUserinfo mUserinfo = muserInfoService.findByUsername(username);
        return findUserByCId(mUserinfo.getCompanyid());
    }
}






