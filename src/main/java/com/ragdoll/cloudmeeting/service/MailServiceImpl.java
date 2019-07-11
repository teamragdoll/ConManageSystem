package com.ragdoll.cloudmeeting.service;

import com.ragdoll.cloudmeeting.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private MuserInfoService muserInfoService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private MeetingService meetingService;

    @Autowired
    private MatchsService matchsService;

    @Value("${spring.mail.username}")
    private String from;


    //发送邮件
    @Override
    public void sendSimpleMail(String to,String title,String content){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from);   //发送者
        mailMessage.setTo(to);       //接受者

        mailMessage.setSubject(title);
        mailMessage.setText(content);

        try {
            mailSender.send(mailMessage);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    //群发会议邀请信息
    @Override
    public String sendMailtoAll(List<String> mails){
        int len = mails.size();
        Meeting meeting = meetingService.findConById(Integer.parseInt(mails.get(0)));
        Company company = companyService.findUser(meeting.getCompanyid());

        Matchs matchs = new Matchs();
//        MatchKY matchKY = new MatchKY();
//        matchKY.setConid(meeting.getConid());
        matchs.setConname(meeting.getConname());
        matchs.setConid(meeting.getConid());

        String results = "";
        String results3 = "";
        String title = "布偶猫会议邀请";

        for(int i = 1;i<len;i++){
            MUserinfo muserinfo = muserInfoService.findUserInfoByEmail(mails.get(i));
            if(muserinfo == null){
                results = mails.get(i) + results;
                String context = meeting.getConname() + "会议，邀请您参与会议，鉴于您非本系统用户，只能通知您会议邀请，具体情况请等待主办方通知";
                sendSimpleMail(mails.get(i),title,context);
            }else{
//                matchKY.setUsername(muserinfo.getMusername());
//                matchs.setMatchKY(matchKY);
                Matchs matchs1 = matchsService.findMatchByConidAndMusername(meeting.getConid(),muserinfo.getMusername());
                if(matchs1 == null) {
                    matchs.setMusername(muserinfo.getMusername());
                    matchs.setConstate(1);
                    matchs.setState(1);
                    matchs.setName(muserinfo.getMname());

                    matchsService.saveMatch(matchs);
                    String context = meeting.getConname() + "会议,邀请您参与会议，具体情况可登录您的账号进行查看";
                    sendSimpleMail(mails.get(i), title, context);
                }else if(matchs1.getState() == 1){
                    String context = meeting.getConname() + "会议,邀请您参与会议，具体情况可登录您的账号进行查看";
                    sendSimpleMail(mails.get(i), title, context);
                }else{
                    results3 = muserinfo.getMname() + results3;
                }
            }
        }
        return results3 + "已参与会议" + results + "非本系统用户!邮件已全部发送成功";
    }
}
