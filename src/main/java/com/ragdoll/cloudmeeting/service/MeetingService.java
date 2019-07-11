package com.ragdoll.cloudmeeting.service;

import com.ragdoll.cloudmeeting.dao.Meeting;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MeetingService {

    //查询所有会议
    List<Meeting> findAllMeetings();

    //添加会议
    String addMeeting(Meeting m);

    //删除会议（根据会议ID进行删除）
    String deleteMeeting(Integer id);

    //修改会议信息
    String changeMeeting(Meeting m);

    //根据公司id查询出该公司所有的会议
    List<Meeting> findConByCompanyId(String id);

    //根据会议ID查询会议
    Meeting findConById(Integer id);
}
