package com.ragdoll.cloudmeeting.controller;

import com.ragdoll.cloudmeeting.dao.Meeting;
import com.ragdoll.cloudmeeting.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RequestMapping("/meeting")
@RestController
@ComponentScan(basePackages = "com.ragdoll.cloudmeeting.service")
public class MeetingController {

    @Autowired
    private MeetingService meetingService;


//    public List<Meeting> findAllMeetings(){
//        List<Meeting> list = meetingService.findAllMeetings();
//        return list;
//    }

    //从数据库查询所有会议，并返回数据给前端
    @RequestMapping("/findAll")
    @ResponseBody
    public List<Meeting> findAllMeeting(){
        List<Meeting> meetings = meetingService.findAllMeetings();
        return meetings;
    }

    //从前端接受会议信息，并保存到数据库中
    @RequestMapping("/add")
    @ResponseBody
    public String addMeetings(@RequestBody Meeting m){
        String msg = meetingService.addMeeting(m);
        return msg;
    }

    //根据前端传入的公司id，查询该公司的所有会议
    @GetMapping("/findByCId/{id}")
    @ResponseBody
    public List<Meeting> findConByCompanyId(@PathVariable("id") String id){
        List<Meeting> meetings = meetingService.findConByCompanyId(id);
        return meetings;
    }

    //根据会议id，查询会议
    @GetMapping("/findMeeting/{id}")
    @ResponseBody
    public Meeting findMeeting(@PathVariable("id") Integer id){
        Meeting meeting = meetingService.findConById(id);
        return meeting;
    }


    //根据会议id，删除会议
    @GetMapping("/deleteMeeting/{id}")
    @ResponseBody
    public String deleteMeeting(@PathVariable("id") Integer id){
        String msg = meetingService.deleteMeeting(id);
        return msg;
    }

    //修改会议
    @PostMapping("/changeMeeting")
    @ResponseBody
    public String changeMeeting(@RequestBody Map<String,String> map){
        Meeting meeting = meetingService.findConById(Integer.parseInt(map.get("conid")));
        meeting.setConinfo(map.get("coninfo"));
        meeting.setConname(map.get("conname"));
        meeting.setCondate(map.get("condate"));
        meeting.setConaddress(map.get("conaddress"));

        String msg = meetingService.changeMeeting(meeting);
        return msg;
    }
}




