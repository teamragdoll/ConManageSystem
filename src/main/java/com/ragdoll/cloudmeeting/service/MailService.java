//洪涛 2017302580282

package com.ragdoll.cloudmeeting.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MailService {
    //发送普通邮件
    void sendSimpleMail(String to,String title,String content);

    //群发会议邀请信息
    String sendMailtoAll(List<String> mails);
}
