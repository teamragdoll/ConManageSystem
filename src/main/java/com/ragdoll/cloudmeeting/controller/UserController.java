//洪涛 2017302580282

package com.ragdoll.cloudmeeting.controller;

import com.ragdoll.cloudmeeting.dao.muser;
import com.ragdoll.cloudmeeting.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@ComponentScan(basePackages = "com.ragdoll.cloudmeeting.service")
public class UserController {

    @Autowired
    private UserService userService;

    //用户登录
    @RequestMapping("/login")
    @ResponseBody
    public String login(@RequestBody muser user){
        muser user2 = userService.findUser(user.getMusername());
        if(user2 != null) {
            if ((user2.getMpassword()).equals(user.getMpassword())) {
                return "登录成功";
            } else return "用户名或密码错误";
        }else{
            return "未查找到用户";
        }
    }

    //用户修改密码
    @RequestMapping("/changeUserPassword")
    @ResponseBody
    public String changeUserPassword(@RequestBody Map<String,String> map){
        muser user = new muser();
        user.setMusername(map.get("musername"));
        user.setMpassword(map.get("password"));
        String msg = login(user);
        if(msg == "登录成功"){
            muser user2 = userService.findUser(user.getMusername());
            user2.setMpassword(map.get("newPassword"));
            userService.changeInfo(user2);
            return "修改成功";
        }else if(msg == "用户名或密码错误"){
            return "原密码错误";
        }else{
            return "系统信息检索错误";
        }

    }
}
