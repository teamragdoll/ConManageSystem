//洪涛 2017302580282

package com.ragdoll.cloudmeeting.service;

import com.ragdoll.cloudmeeting.dao.MatchDao;
import com.ragdoll.cloudmeeting.dao.Meeting;
import com.ragdoll.cloudmeeting.dao.MeetingDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@ComponentScan(basePackages = "com.ragdoll.cloudmeeting.dao")
public class MeetingServiceImpl implements MeetingService {

    @Autowired
    private MeetingDao meetingdao;

    @Autowired
    private MatchDao matchDao;

    public MeetingServiceImpl(){
    }

    //查询所有会议
    @Override
    public List<Meeting> findAllMeetings() {
        return meetingdao.findAll();
    }

    //添加会议
    @Override
    public String addMeeting(Meeting m) {
        try {
            Meeting meeting = meetingdao.findByConid(m.getConid());
            if(meeting == null) {
                meetingdao.save(m);
                return "添加成功";
            }else{
                return "该会议已经存在";
            }
        }catch (Exception e){
            return "添加失败";
        }
    }

    //修改会议信息
    @Override
    @Transactional
    public String changeMeeting(Meeting m) {
        try {
            meetingdao.save(m);
            matchDao.deleteByConidAndState(m.getConid(),1);
            matchDao.update(2,m.getConid());
            return "修改成功";
        }catch(Exception e){
            return "修改失败";
        }
    }

    //通过公司ID查询该公司所有会议
    @Override
    public List<Meeting> findConByCompanyId(String id) {
        List<Meeting> meetings = meetingdao.findAllByCompanyid(id);
        return meetings;
    }

    //通过ID查询会议
    @Override
    public Meeting findConById(Integer id){
        Meeting meeting = meetingdao.findByConid(id);
        if(meeting == null){
            return null;
        }
        else {
            return meeting;
        }
    }

    //通过ID删除会议
    @Override
    public String deleteMeeting(Integer id) {
        try {
            Meeting meeting = meetingdao.findByConid(id);
            if (meeting == null) {
                return "不存在该会议";
            } else {
                meetingdao.deleteByConid(id);
                matchDao.update(3,id);
                return "删除会议";
            }
        }catch (Exception e){
            return "删除错误" + e;
        }
    }
}
