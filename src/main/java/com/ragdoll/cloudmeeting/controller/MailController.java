//洪涛 2017302580282

package com.ragdoll.cloudmeeting.controller;

import com.ragdoll.cloudmeeting.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
@ComponentScan(basePackages = "com.ragdoll.cloudmeeting.service")
public class MailController {

    @Autowired
    private MailService mailService;

    @Autowired
    private MuserInfoService muserInfoService;

    @Autowired
    private CompanyService companyService;

    @Value("${spring.mail.username}")
    private String from;

    //用户注册发送验证码
    @RequestMapping("/sendMail")
    @ResponseBody
    public String sendMail(@RequestBody Map<String,String> map){

        String mail = map.get("memail");
        String name = map.get("musername");

        String msg = muserInfoService.isExitUserinfo(mail,name);

        if(msg == "邮箱可以用来注册") {
            Random random = new Random();
            String result = "";
            for (int i = 0; i < 6; i++) {
                result = random.nextInt(10) + result;
            }
            mailService.sendSimpleMail(mail, "布偶猫公司", "您的验证码为：" + result);
            return result;
        }else if(msg == "查询失败"){
            return "信息检验失败";
        }else{
            return msg;
        }
    }

    //公司注册发送验证码
    @RequestMapping("/sendCompanyMail")
    @ResponseBody
    public String sendCompanyMail(@RequestBody String mail){
        String msg = companyService.isExitEmail(mail);
        if(msg == "邮箱未被注册"){
            Random random = new Random();
            String result = "";
            for(int i = 0;i<6;i++){
                result = random.nextInt(10) + result;
            }
            mailService.sendSimpleMail(mail,"布偶猫公司","您的验证码为" + result);
            return result;
        }else{
            return "邮箱已被注册";
        }
    }

    //邮箱通知嘉宾
//    @RequestMapping("/sendMailtoVip")
//    @ResponseBody
//    public String sendMailtoVip(@RequestBody Map<String,String>[] maps){
//        int len = maps.length;
//        for(int i = 0;i<len;i++){
//            String mail = maps[i].get("memail");
//            String username = maps[i].get("musername");
//            String name = maps[i].get("mname");
//
//            String title = "布偶猫会议通知";
//            String context = "会议邀请";
//
//            mailService.sendSimpleMail(from ,mail,title,context);
//        }
//        return "邮件全部发送完毕";
//    }

    //邀请嘉宾发送邀请信息
    @RequestMapping("/sendMailtoVip")
    @ResponseBody
    public String sendMailtoVip(@RequestBody List<String> mails){
//        int len = mails.size();
//        Meeting meeting = meetingService.findConById(Integer.parseInt(mails.get(0)));
//        Company company = companyService.findUser(meeting.getCompanyid());
//
//        Matchs matchs = new Matchs();
//        MatchKY matchKY = new MatchKY();
//        matchKY.setConid(meeting.getConid());
//        matchs.setConname(meeting.getConname());
//
//        String results = "";
//        String title = "布偶猫会议邀请";
//
//
//        for(int i = 1;i<len;i++){
//            MUserinfo muserinfo = muserInfoService.findUserInfoByEmail(mails.get(i));
//            if(muserinfo == null){
//                results = mails.get(i) + results;
//                String context = meeting.getConname() + "，邀请您参与会议，鉴于您非本系统用户，只能通知您会议邀请，具体情况请等待主办方通知";
//
//            }else{
//                matchKY.setUsername(muserinfo.getMusername());
//                matchs.setMatchKY(matchKY);
//                matchs.setConstate(0);
//                matchs.setState(0);
//
//                matchService.addMatch(matchs);
//
//            }
//        }
        return mailService.sendMailtoAll(mails);
    }
}

