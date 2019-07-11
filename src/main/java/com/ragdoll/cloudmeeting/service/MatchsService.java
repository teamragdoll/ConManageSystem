package com.ragdoll.cloudmeeting.service;

import com.ragdoll.cloudmeeting.dao.Matchs;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Service
public interface MatchsService {

    //添加会议参与详情
    String saveMatch(Matchs m);

    //修改会议详情中的会议状态
//    String changeMatchsConstateByConid(Integer state,Integer conid);

    //通过用户名查找会议参加表
    List<Matchs> findMatchsByMusername(String name);

    //通过用户名和会议Id查找会议参加表
    Matchs findMatchByConidAndMusername(Integer id,String username);

    //更改会议参与表的会议状态
    String changeMatchCstateByConidAndName(Integer state,Integer conid,String musername);

    //更改会议参与表的用户状态
//    String changeMatchStateByConidAndName(Integer state,Integer conid,String musername);

    //删除会议参与表的元组
    String deleteMatch(Integer id,String username);

    //根据用户名和会议id进行状态判断
    String getState(Integer conid,String username);

    //根据会议名和参与状态查找会议参与表
    List<Matchs> findByConidAndState(Integer conid,Integer state);
}
