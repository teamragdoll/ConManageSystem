package com.ragdoll.cloudmeeting.controller;

import com.ragdoll.cloudmeeting.dao.Matchs;
import com.ragdoll.cloudmeeting.service.MatchsService;
import com.ragdoll.cloudmeeting.service.MeetingService;
import com.ragdoll.cloudmeeting.service.MuserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;

@RequestMapping("/match")
@RestController
@ComponentScan(basePackages = "com.ragdoll.cloudmeeting.service")
public class MatchsController {

    @Autowired
    private MatchsService matchsService;

    @Autowired
    private MeetingService meetingService;

    @Autowired
    private MuserInfoService muserInfoService;

    //通过用户名查询会议参与表
    @RequestMapping("/findMatchsByMusername/{name}")
    @ResponseBody
    public List<Matchs> findMatchsByMusername(@PathVariable("name") String name){
        return matchsService.findMatchsByMusername(name);
    }

    //用户已阅读会议更改，更改会议参与表
    @RequestMapping("/matchChange")
    @ResponseBody
    public String matchChange(@RequestBody Map<String,String> map){
        Integer id = Integer.parseInt(map.get("conid"));
        String username = map.get("musername");
        return matchsService.changeMatchCstateByConidAndName(1,id,username);
    }

//    //用户同意参与会议，更改会议参与表
//    @RequestMapping("/matchAccept")
//    @ResponseBody
//    public String matchAccept(@RequestBody Map<String,String> map){
//        Integer id = Integer.parseInt(map.get("conid"));
//        String username = map.get("musername");
//        return matchService.changeMatchStateByConidAndName(2,id,username);
//    }

    //用户已阅读会议取消信息，更改会议参与表
    @RequestMapping("/matchDelete")
    @ResponseBody
    public String matchDelete(@RequestBody Map<String,String> map){
        Integer id = Integer.parseInt(map.get("conid"));
        String username = map.get("musername");
        return matchsService.deleteMatch(id,username);
    }

    //用户报名参与会议，更改会议参与表
    @RequestMapping("/joinMeeting")
    @ResponseBody
    public String joinMeeting(@RequestBody Map<String,String> map){
        String username = map.get("musername");
        Integer conid = Integer.parseInt(map.get("conid"));
        Matchs matchs = matchsService.findMatchByConidAndMusername(conid,username);
        if(matchs == null) {
            String conname = meetingService.findConById(conid).getConname();
            String mname = muserInfoService.findByUsername(username).getMname();

            Matchs match = new Matchs();
            match.setState(3);
            match.setConstate(1);
            match.setConname(conname);
            match.setName(mname);
            match.setConid(conid);
            match.setMusername(username);

            matchsService.saveMatch(match);
            return "报名成功";
        }else{
            if(matchs.getState() == 1){
                matchs.setState(2);
                matchsService.saveMatch(matchs);
                return "您为嘉宾，报名成功";
            }else{
                return "您已报名过此会议";
            }
        }
    }

    //根据会议和用户判断用户在该会议的状态（以报名，未报名，邀请未接受）
    @RequestMapping("/getState")
    @ResponseBody
    public String getState(@RequestBody Map<String,String> map){
        String name = map.get("musername");
        Integer conid = Integer.parseInt(map.get("conid"));
        return matchsService.getState(conid,name);
    }

    //根据会议显示参与会议的嘉宾情况
    @RequestMapping("/getJoinedVip/{conid}")
    @ResponseBody
    public String[] getJoinedVip(@PathVariable Integer conid){
        List<Matchs> matchs = matchsService.findByConidAndState(conid,2);
        int len = matchs.size();
        String[] nameStr = new String[len];
        for(int i=0;i<len;i++){
            nameStr[i] = matchs.get(i).getName();
        }
        return nameStr;
    }

    //根据会议id查找会议参加表获得参与会议参与人数
    @RequestMapping("/getNumberOfCon/{conid}")
    @ResponseBody
    public int getNumberOfCon(@PathVariable Integer conid){
        List<Matchs> matchs1 = matchsService.findByConidAndState(conid,2);
        List<Matchs> matchs2 = matchsService.findByConidAndState(conid,3);
        return matchs1.size() + matchs2.size();
    }
}


