//洪涛 2017302580282

package com.ragdoll.cloudmeeting.controller;

import com.ragdoll.cloudmeeting.dao.Company;
import com.ragdoll.cloudmeeting.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Random;

@RequestMapping("/company")
@RestController
@ComponentScan(basePackages = "com.ragdoll.cloudmeeting.service")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    //公司账户登录
    @RequestMapping("/login")
    @ResponseBody
    public String companyLogin(@RequestBody Map<String,String> map){
        Company company = companyService.findUser(map.get("companyid"));
        if(company != null){
            if(company.getPassword().equals(map.get("password"))){
                return "登录成功";
            }else{
                return "用户名或密码错误";
            }
        }else{
            return "未查找到用户";
        }
    }

    //根据id查询公司信息
    @RequestMapping("/findComById/{id}")
    @ResponseBody
    public Company findComById(@PathVariable("id") String id){
        return companyService.findUser(id);
    }

    //公司账户注册
    @RequestMapping("/register")
    @ResponseBody
    public String companyRegister(@RequestBody Company company){
        boolean isExitCompanyId;
        String msg = "注册失败";
        isExitCompanyId = true;
        Random random = new Random();
        String companyId = "";
        while(isExitCompanyId) {
            for (int i = 0; i < 6; i++) {
                companyId = random.nextInt(10) + companyId;
            }
            if (companyService.findUser(companyId) == null) {
                isExitCompanyId = false;
                company.setCompanyid(companyId);
                msg = companyService.addCompany(company);
            }
        }
        if(msg == "保存成功"){
            return companyId;
        }else{
            return "注册失败";
        }
    }

    //公司修改资料
    @RequestMapping("/changeInfo")
    @ResponseBody
    public String changeCompanyInfo(@RequestBody Map<String,String> map){
        String companyId = map.get("companyid");
        String userinfo = map.get("userinfo");
        String companyName = map.get("companyname");
        Company company = companyService.findUser(companyId);
        company.setCompanyname(companyName);
        company.setUserinfo(userinfo);
        String msg = companyService.changeCompanyInfo(company);
        if(msg == "修改成功"){
            return "修改成功";
        }else{
            return "修改失败";
        }
    }

    //公司修改账号密码
    @RequestMapping("/changePassword")
    @ResponseBody
    public String changePassword(@RequestBody Map<String,String> map){
        String msg = companyLogin(map);
        if(msg == "登录成功"){
            Company company = companyService.findUser(map.get("companyid"));
            company.setPassword(map.get("newPassword"));
            companyService.changeCompanyInfo(company);
            return "修改成功";
        }else if(msg == "用户名或密码错误"){
            return "原密码错误";
        }else{
            return "未找到用户";
        }
    }
}


